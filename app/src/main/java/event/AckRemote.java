package event;

import com.boffo.event.api.AckService;
import com.boffo.event.api.ClientInfo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.apache.thrift.TException;

public class AckRemote implements AckService.Iface{
    private DBCollection _ackColl;

    public AckRemote(DB db) {
        _ackColl = db.getCollection("ack");
    }

    @Override
    public void ack(ClientInfo client, String eventId) throws TException {
        // do nothing
    }
}
