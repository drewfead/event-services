package event;

import com.mongodb.*;

import java.util.*;
import java.util.stream.Collectors;

public class EventDAO {
    private static final String
        ID = "eventId",
        CREATED = "created",
        LAST_MODIFIED = "lastModified",
        VERSION = "version",
        CLIENT = "client",
        ATTENDEES = "attendees",
        STATE = "deleted";

    private static <K,V> Map<K,V> putAll(Map<K,V> map, Object... args){
         if (args.length % 2 != 0) {
             throw new IllegalArgumentException("Must have an even number of arguments: " + Arrays.toString(args));
         }
         for (int i=0; i<args.length; i+=2) {
             map.put((K)args[i], (V)args[i+1]);
         }
         return map;
     }

    private static <K,V> Map<K, V> hashMap(Object...args) {
        	return putAll(new HashMap<K,V>(), args);
        }

    private static BasicDBObject obj(Object...args) {
        Map<String, Object> m = hashMap(args);
        BasicDBObject o = new BasicDBObject();
        for (Map.Entry<String, Object> e : m.entrySet()) {
            o.put(e.getKey(), e.getValue());
        }
        return o;
    }


    private static BasicDBList serialize(List<String> attendees) {
        return attendees.stream()
            .collect(Collectors.toCollection(BasicDBList::new));
    }

    private static List<String> deserialize(BasicDBList bson) {
        return bson.stream()
            .map(o -> (String) o).collect(Collectors.toList());
    }

    public static void create(DBCollection coll, String client, UUID id, com.boffo.event.api.Event e){
        final Date now = new Date(System.currentTimeMillis());

        DBObject b = obj(
            ID, id.toString(),
            CREATED, now,
            LAST_MODIFIED, now,
            VERSION, 0L,
            CLIENT, client,
            ATTENDEES, serialize(e.getParams().getOngoing().getAttendeeIds())
        );
        try{
            coll.insert(b, WriteConcern.ACKNOWLEDGED);
        }catch (DuplicateKeyException de){
            update(coll, id, e.getParams().getOngoing().getAttendeeIds());
        }
    }

    public static Event read(DBObject b) {
        Event e = new Event();
        e.setId(UUID.fromString((String) b.get(ID)));
        e.setCreated((long) b.get(CREATED));
        e.setLastModified((long) b.get(LAST_MODIFIED));
        e.setVersion((long) b.get(VERSION));
        e.setClient((String) b.get(CLIENT));
        e.setAttendees(deserialize((BasicDBList) b.get(ATTENDEES)));
        e.setState(Event.EventState.valueOf((String) b.get(STATE)));
        return e;
    }

    public static Event read(DBCollection coll, UUID id) {
        DBObject b = getLatest(coll, id);
        if (b == null) { return null; }
        return read(b);
    }

    public static boolean update(DBCollection coll, UUID id, List<String> attendees) {
        return false;
    }

    private static DBObject getLatest(DBCollection coll, UUID id){
        return coll.findOne(
            obj(ID, id.toString()),
            null,
            obj(VERSION, -1)
        );
    }
}
