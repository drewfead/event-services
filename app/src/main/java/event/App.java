package event;

import com.aphyr.riemann.client.RiemannClient;
import com.boffo.event.api.AckService;
import com.boffo.event.api.EventService;
import com.boffo.event.api.eventConstants;
import com.mongodb.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class App {
    private static final String RIEMANN_HOST = "localhost";
    private static final int RIEMANN_PORT = 5557;
    public static final int EDGE_PORT = 9999;

    private static final Log LOG = LogFactory.getLog(App.class);

    private MongoClient _mongo;
    private TServer _thriftServer;

    private RiemannClient _riemann;
    private Connection _rabbitConn;
    private Publisher _publisher;

    private static String runtime() {
        final String e = System.getenv("runtimeEnvironment");
        if (StringUtils.isEmpty(e)) {
            return "local";
        }
        return e;
    }

    private static boolean isLocal() { return "local".equals(runtime()); }

    private static String host(){
        try{
            return java.net.InetAddress.getLocalHost().getHostName();
        }catch(Exception e){
            return null;
        }
    }

    private static ConnectionFactory rabbitFactory() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("localhost");
        return cf;
    }

    private static void register(TMultiplexedProcessor multi, String name, TProcessor p, RiemannClient client) {
        TProcessor metricsProcessor = new RiemannEnabledProcessor(p, name, client);
        multi.registerProcessor(name, metricsProcessor);
        TProcessor exceptionProcessor = new ExceptionUnwrappingProcessor(p);
        multi.registerProcessor(name, exceptionProcessor);
    }

    private static TServer initThriftServer(EventService.Iface eventService, AckService.Iface ackService,
                                            int port, RiemannClient client) throws TTransportException {

        TMultiplexedProcessor p = new TMultiplexedProcessor();
        register(p, eventConstants.EVENT_SERVICE, new EventService.Processor<>(eventService), client);
        register(p, eventConstants.ACK_SERVICE, new AckService.Processor<>(ackService), client);

        TServerTransport trans = new TServerSocket(port);
        final TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(trans).processor(p));

        new Thread(server::serve).start();

        return server;
    }

    public void start() throws Exception {
        _riemann = RiemannClient.tcp(RIEMANN_HOST, RIEMANN_PORT);
        try {
            _riemann.connect();
        } catch (Exception e) {
            LOG.error("Could not connect to riemann on first try, may succeed later", e);
        }

        if (isLocal()) {
            _mongo = new MongoClient();
        }

        final DB db = _mongo.getDB("event");
        db.setReadPreference(ReadPreference.primary());
        db.setWriteConcern(WriteConcern.ACKNOWLEDGED);

        final ConnectionFactory cf = rabbitFactory();
        if (isLocal()) {
            cf.setVirtualHost("/");
            cf.setUsername("guest");
            cf.setPassword("guest");
            cf.setRequestedHeartbeat(10);
            cf.setAutomaticRecoveryEnabled(true);
            cf.setNetworkRecoveryInterval(5000);
        }

        LOG.info("Rabbit connection config: [host:" + cf.getHost() + ", port:" + cf.getPort() +
                ", connectionTimeout:" + cf.getConnectionTimeout() + ", channelMax: " + cf.getRequestedChannelMax() +
                ", frameMax:" + cf.getRequestedFrameMax() + ", heartbeat:" + cf.getRequestedHeartbeat() +
                ", virtualHost:" + cf.getVirtualHost());

        _rabbitConn = cf.newConnection();

        final String exchangeName = "event-delivery";

        _publisher = new Publisher(exchangeName, cf);
        _publisher.start();
        _publisher.startRedelivery();
        _publisher.startExpire();

        final EventRemote er = new EventRemote(db);
        final AckRemote ar = new AckRemote(db);

        _thriftServer = initThriftServer(er, ar, EDGE_PORT, _riemann);
    }


    public void stop() {
        _publisher.stopRedelivery();
        _publisher.stopExpire();
        _publisher.stop();

        try {
            _rabbitConn.close();
        }
        catch (Exception e) {
            LOG.debug("failed to close rabbit connection on shutdown", e);
        }

        _thriftServer.stop();
        _mongo.close();

        if(_riemann != null){
            try{
                _riemann.close();
            }
            catch (Exception e){
                LOG.debug("failed to disconnect riemann on shutdown", e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final App app = new App();
        app.start();
        LOG.info("starting server on port " + EDGE_PORT);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                LOG.info("shutting down...");
                app.stop();
                LOG.info("shutdown complete");
            }
        });
    }
}
