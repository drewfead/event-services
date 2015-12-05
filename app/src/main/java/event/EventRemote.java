package event;

import com.boffo.event.api.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.UUID;

public class EventRemote implements EventService.Iface{
    private final DBCollection _eventColl;

    public EventRemote(DB db) {
        _eventColl = db.getCollection("event");
    }

    @Override
    public void create(ClientInfo client, String eventId, com.boffo.event.api.Event event) throws TException {
        EventDAO.create(
            _eventColl,
            client.getClientId(),
            UUID.fromString(eventId),
            event
        );
    }

    @Override
    public void update(ClientInfo client, String eventId, com.boffo.event.api.Event event) throws TException {
         boolean success = EventDAO.update(
             _eventColl,
             UUID.fromString(eventId),
             event.getParams().getOngoing().getAttendeeIds()
         );
        if(!success) {
            throw new EventError(ErrorType.NOT_FOUND, "couldn't find event:" + eventId + " to update");
        }
    }

    @Override
    public void cancel(ClientInfo client, String eventId) throws TException {
         boolean success = EventDAO.update(
            _eventColl,
            UUID.fromString(eventId),
            new ArrayList<>()
         );
        if(!success) {
            throw new EventError(ErrorType.NOT_FOUND, "couldn't find event:" + eventId + " to cancel");
        }
    }

    @Override
    public com.boffo.event.api.Event find(ClientInfo client, String eventId) throws TException {
        Event a = EventDAO.read(_eventColl, UUID.fromString(eventId));
        if(a == null) {
            throw new EventError(ErrorType.NOT_FOUND, "couldn't find event:" + eventId + " to read");
        }
        OngoingParams p = new OngoingParams();
        p.setAttendeeIds(a.attendees());

        com.boffo.event.api.Event b = new com.boffo.event.api.Event();
        b.setParams(EventParams.ongoing(p));

        return b;
    }
}
