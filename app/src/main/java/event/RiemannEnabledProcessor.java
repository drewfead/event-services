package event;

import com.aphyr.riemann.client.EventDSL;
import com.aphyr.riemann.client.RiemannClient;
import com.boffo.event.api.EventError;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;

import java.util.HashMap;
import java.util.Map;

public class RiemannEnabledProcessor implements TProcessor{

    private final RiemannClient _client;
    private final TProcessor _processor;
    private final String _serviceName;

    public RiemannEnabledProcessor(TProcessor processor, String serviceName, RiemannClient client){
        _processor = processor;
        _serviceName = serviceName;
        _client = client;
    }

    @Override
    public boolean process(TProtocol inProtocol, TProtocol outProtocol) throws TException{
        Long startTime = 0l;
        String methodName = null;
        String exThrown = null;
        String state = null;
        try{
            TMessage message = inProtocol.readMessageBegin();
            methodName = message.name;
            startTime = System.currentTimeMillis();
            Boolean ret = _processor.process(inProtocol, outProtocol);
            state = "ok";
            return ret;
        }catch(Exception ex){
            exThrown = ex.getClass().getSimpleName();
            if(ex instanceof EventError){
                state = "warn";
            }else{
                state = "crit";
            }
            throw ex;
        }finally {
            if(_client != null){
                Long endTime = System.currentTimeMillis();
                Long duration = endTime - startTime;
                Map<String, String> attrs = new HashMap<>();
                attrs.put("method", methodName);
                attrs.put("duration", duration.toString());
                if(exThrown != null){
                    attrs.put("exception", exThrown);
                }

                EventDSL dsl = base(_client);
                dsl.service("event-service-remote." + methodName);
                dsl.metric(1);
                dsl.state(state);
                dsl.attributes(attrs);
                dsl.send();
            }
        }
    }

    private static EventDSL base(RiemannClient client){
        EventDSL dsl = client.event();
        dsl.host("localhost")
            .attribute("port", String.valueOf(App.EDGE_PORT))
            .attribute("app", "event-services")
            .attribute("env", "local");
        return dsl;
    }



}
