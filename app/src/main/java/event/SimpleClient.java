package event;

import com.boffo.event.api.*;
import com.boffo.event.api.Event;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class SimpleClient {
    private static final String THRIFT_HOST = "localhost";
    private static final int THRIFT_PORT = 9999;
    private static final int SOCKET_TIMEOUT = 500;

    private static EventService.Client initClient(TTransport trans) {
        TProtocol protocol = new TBinaryProtocol(trans);
        TMultiplexedProtocol p = new TMultiplexedProtocol(protocol, eventConstants.EVENT_SERVICE);
        return new EventService.Client(p);
    }

    private static void createEvent(ClientInfo ci, String eventId, Event event) {
        TTransport trans = new TSocket(THRIFT_HOST, THRIFT_PORT, SOCKET_TIMEOUT);
        try {
            EventService.Client client = initClient(trans);
            trans.open();
            client.create(ci, eventId, event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            trans.close();
        }
    }

    public static void main(String[] args) {
        ClientInfo ci = new ClientInfo();
        ci.apiKey = "THIS!S4N4P!K3Y";
        ci.clientId = "simple";

        String eventId = UUID.randomUUID().toString();

        Event event = new Event();
        event.context = new HashMap<>();
        event.context.put("description", "maybe I put an event description here");

        OngoingParams op = new OngoingParams();
        op.attendeeIds = Collections.singletonList(UUID.randomUUID().toString());
        event.params = EventParams.ongoing(op);

        createEvent(ci, eventId, event);
    }

}
