package event;

import com.boffo.event.api.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import java.util.HashMap;
import java.util.Map;

public class SimpleApp {
    public static final int EDGE_PORT = 9999;

    private static final Log LOG = LogFactory.getLog(App.class);

    private TServer _thriftServer;
    private Map<String, com.boffo.event.api.Event> _events = new HashMap<>();

    public void start() throws Exception {
        TServerTransport trans = new TServerSocket(EDGE_PORT);
        final EventService.Iface eventService = new EventService.Iface() {
            @Override
            public void create(ClientInfo client, String eventId, com.boffo.event.api.Event event) throws TException {
                LOG.info("creating event:" + eventId + " {" + event + "}");
                _events.put(eventId, event);
            }

            @Override
            public void update(ClientInfo client, String eventId, com.boffo.event.api.Event event) throws TException {
                LOG.info("updating event:" + eventId + " {" + event + "}");
                if(!_events.containsKey(eventId)) {
                    throw new EventError(ErrorType.NOT_FOUND, "uh oh! couldn't find the event!");
                }
                _events.put(eventId, event);
            }

            @Override
            public void cancel(ClientInfo client, String eventId) throws TException {
                LOG.info("canceling event:" + eventId);
                if(!_events.containsKey(eventId)) {
                    throw new EventError(ErrorType.NOT_FOUND, "uh oh! couldn't find the event!");
                }
                _events.remove(eventId);
            }

            @Override
            public com.boffo.event.api.Event find(ClientInfo client, String eventId) throws TException {
                com.boffo.event.api.Event toReturn = _events.get(eventId);
                if(toReturn == null) {
                    throw new EventError(ErrorType.NOT_FOUND, "uh oh! couldn't find the event!");
                }
                return toReturn;
            }
        };

        _thriftServer = new TSimpleServer(new Args(trans).processor(new EventService.Processor<>(eventService)));
        new Thread(_thriftServer::serve).start();
    }


    public void stop() {
        _thriftServer.stop();
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
