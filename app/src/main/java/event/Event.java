package event;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Event {
    private UUID _id;
    private long _created;
    private long _lastModified;
    private long _version;
    private String _client;
    private List<String> _attendees;
    private EventState _state;

    public enum EventState {
        PENDING,
        CANCELED,
        ONGOING,
        HIATUS,
        COMPLETE
    }

    Event() {}

    public Event(UUID id, long created, long lastModified, long version, String client,
                 List<String> attendees, EventState state) {
        _id = id;
        _created = created;
        _lastModified = lastModified;
        _version = version;
        _client = client;
        _attendees = attendees;
        _state = state;
    }

    public static class Converter {
        public static Event convert(String id, com.boffo.event.api.Event e) {
            return new Event();
        }

        public static com.boffo.event.api.Event convert(Event e) {
            return new com.boffo.event.api.Event();
        }
    }

    public UUID id() { return _id; }
    public void setId(UUID id) {_id = id;}
    public long created() { return _created; }
    public void setCreated(long created) {_created = created; }
    public long lastModified() { return _lastModified; }
    public void setLastModified(long lastModified) {_lastModified = lastModified; }
    public long version() { return _version; }
    public void setVersion(long version) {_version = version; }

    public String client() { return _client; }
    public void setClient(String client) {_client = client; }
    public List<String> attendees() { return _attendees; }
    public void setAttendees(List<String> attendees) { _attendees = attendees; }
    public EventState state() { return _state; }
    public void setState(EventState state) {_state = state; }
}
