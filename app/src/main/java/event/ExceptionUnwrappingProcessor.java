package event;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;

public class ExceptionUnwrappingProcessor implements TProcessor {
    private TProcessor processor;

    public ExceptionUnwrappingProcessor(TProcessor processor) {
        this.processor = processor;
    }

    @Override
    public boolean process(TProtocol inProtocol, TProtocol outProtocol) throws TException {
        try {
            return processor.process(inProtocol, outProtocol);
        } catch (RuntimeException re) {
            if(ExceptionUtils.getRootCause(re) instanceof TException) {
                throw (TException) ExceptionUtils.getRootCause(re);
            } else {
                throw re;
            }
        }
    }
}
