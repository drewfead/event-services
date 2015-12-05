/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.boffo.event.api;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-12-4")
public class AckService {

  public interface Iface {

    public void ack(ClientInfo client, String eventId) throws EventError, org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void ack(ClientInfo client, String eventId, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public void ack(ClientInfo client, String eventId) throws EventError, org.apache.thrift.TException
    {
      send_ack(client, eventId);
      recv_ack();
    }

    public void send_ack(ClientInfo client, String eventId) throws org.apache.thrift.TException
    {
      ack_args args = new ack_args();
      args.setClient(client);
      args.setEventId(eventId);
      sendBase("ack", args);
    }

    public void recv_ack() throws EventError, org.apache.thrift.TException
    {
      ack_result result = new ack_result();
      receiveBase(result, "ack");
      if (result.e != null) {
        throw result.e;
      }
      return;
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void ack(ClientInfo client, String eventId, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      ack_call method_call = new ack_call(client, eventId, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class ack_call extends org.apache.thrift.async.TAsyncMethodCall {
      private ClientInfo client;
      private String eventId;
      public ack_call(ClientInfo client, String eventId, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.client = client;
        this.eventId = eventId;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("ack", org.apache.thrift.protocol.TMessageType.CALL, 0));
        ack_args args = new ack_args();
        args.setClient(client);
        args.setEventId(eventId);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public void getResult() throws EventError, org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        (new Client(prot)).recv_ack();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("ack", new ack());
      return processMap;
    }

    public static class ack<I extends Iface> extends org.apache.thrift.ProcessFunction<I, ack_args> {
      public ack() {
        super("ack");
      }

      public ack_args getEmptyArgsInstance() {
        return new ack_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public ack_result getResult(I iface, ack_args args) throws org.apache.thrift.TException {
        ack_result result = new ack_result();
        try {
          iface.ack(args.client, args.eventId);
        } catch (EventError e) {
          result.e = e;
        }
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("ack", new ack());
      return processMap;
    }

    public static class ack<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, ack_args, Void> {
      public ack() {
        super("ack");
      }

      public ack_args getEmptyArgsInstance() {
        return new ack_args();
      }

      public AsyncMethodCallback<Void> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<Void>() { 
          public void onComplete(Void o) {
            ack_result result = new ack_result();
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            ack_result result = new ack_result();
            if (e instanceof EventError) {
                        result.e = (EventError) e;
                        result.setEIsSet(true);
                        msg = result;
            }
             else 
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, ack_args args, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws TException {
        iface.ack(args.client, args.eventId,resultHandler);
      }
    }

  }

  public static class ack_args implements org.apache.thrift.TBase<ack_args, ack_args._Fields>, java.io.Serializable, Cloneable, Comparable<ack_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ack_args");

    private static final org.apache.thrift.protocol.TField CLIENT_FIELD_DESC = new org.apache.thrift.protocol.TField("client", org.apache.thrift.protocol.TType.STRUCT, (short)1);
    private static final org.apache.thrift.protocol.TField EVENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("eventId", org.apache.thrift.protocol.TType.STRING, (short)2);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new ack_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new ack_argsTupleSchemeFactory());
    }

    public ClientInfo client; // required
    public String eventId; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      CLIENT((short)1, "client"),
      EVENT_ID((short)2, "eventId");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // CLIENT
            return CLIENT;
          case 2: // EVENT_ID
            return EVENT_ID;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.CLIENT, new org.apache.thrift.meta_data.FieldMetaData("client", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ClientInfo.class)));
      tmpMap.put(_Fields.EVENT_ID, new org.apache.thrift.meta_data.FieldMetaData("eventId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ack_args.class, metaDataMap);
    }

    public ack_args() {
    }

    public ack_args(
      ClientInfo client,
      String eventId)
    {
      this();
      this.client = client;
      this.eventId = eventId;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public ack_args(ack_args other) {
      if (other.isSetClient()) {
        this.client = new ClientInfo(other.client);
      }
      if (other.isSetEventId()) {
        this.eventId = other.eventId;
      }
    }

    public ack_args deepCopy() {
      return new ack_args(this);
    }

    @Override
    public void clear() {
      this.client = null;
      this.eventId = null;
    }

    public ClientInfo getClient() {
      return this.client;
    }

    public ack_args setClient(ClientInfo client) {
      this.client = client;
      return this;
    }

    public void unsetClient() {
      this.client = null;
    }

    /** Returns true if field client is set (has been assigned a value) and false otherwise */
    public boolean isSetClient() {
      return this.client != null;
    }

    public void setClientIsSet(boolean value) {
      if (!value) {
        this.client = null;
      }
    }

    public String getEventId() {
      return this.eventId;
    }

    public ack_args setEventId(String eventId) {
      this.eventId = eventId;
      return this;
    }

    public void unsetEventId() {
      this.eventId = null;
    }

    /** Returns true if field eventId is set (has been assigned a value) and false otherwise */
    public boolean isSetEventId() {
      return this.eventId != null;
    }

    public void setEventIdIsSet(boolean value) {
      if (!value) {
        this.eventId = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case CLIENT:
        if (value == null) {
          unsetClient();
        } else {
          setClient((ClientInfo)value);
        }
        break;

      case EVENT_ID:
        if (value == null) {
          unsetEventId();
        } else {
          setEventId((String)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case CLIENT:
        return getClient();

      case EVENT_ID:
        return getEventId();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case CLIENT:
        return isSetClient();
      case EVENT_ID:
        return isSetEventId();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof ack_args)
        return this.equals((ack_args)that);
      return false;
    }

    public boolean equals(ack_args that) {
      if (that == null)
        return false;

      boolean this_present_client = true && this.isSetClient();
      boolean that_present_client = true && that.isSetClient();
      if (this_present_client || that_present_client) {
        if (!(this_present_client && that_present_client))
          return false;
        if (!this.client.equals(that.client))
          return false;
      }

      boolean this_present_eventId = true && this.isSetEventId();
      boolean that_present_eventId = true && that.isSetEventId();
      if (this_present_eventId || that_present_eventId) {
        if (!(this_present_eventId && that_present_eventId))
          return false;
        if (!this.eventId.equals(that.eventId))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_client = true && (isSetClient());
      list.add(present_client);
      if (present_client)
        list.add(client);

      boolean present_eventId = true && (isSetEventId());
      list.add(present_eventId);
      if (present_eventId)
        list.add(eventId);

      return list.hashCode();
    }

    @Override
    public int compareTo(ack_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetClient()).compareTo(other.isSetClient());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetClient()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.client, other.client);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetEventId()).compareTo(other.isSetEventId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetEventId()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.eventId, other.eventId);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("ack_args(");
      boolean first = true;

      sb.append("client:");
      if (this.client == null) {
        sb.append("null");
      } else {
        sb.append(this.client);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("eventId:");
      if (this.eventId == null) {
        sb.append("null");
      } else {
        sb.append(this.eventId);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (client != null) {
        client.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class ack_argsStandardSchemeFactory implements SchemeFactory {
      public ack_argsStandardScheme getScheme() {
        return new ack_argsStandardScheme();
      }
    }

    private static class ack_argsStandardScheme extends StandardScheme<ack_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, ack_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // CLIENT
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.client = new ClientInfo();
                struct.client.read(iprot);
                struct.setClientIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // EVENT_ID
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.eventId = iprot.readString();
                struct.setEventIdIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, ack_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.client != null) {
          oprot.writeFieldBegin(CLIENT_FIELD_DESC);
          struct.client.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.eventId != null) {
          oprot.writeFieldBegin(EVENT_ID_FIELD_DESC);
          oprot.writeString(struct.eventId);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class ack_argsTupleSchemeFactory implements SchemeFactory {
      public ack_argsTupleScheme getScheme() {
        return new ack_argsTupleScheme();
      }
    }

    private static class ack_argsTupleScheme extends TupleScheme<ack_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, ack_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetClient()) {
          optionals.set(0);
        }
        if (struct.isSetEventId()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetClient()) {
          struct.client.write(oprot);
        }
        if (struct.isSetEventId()) {
          oprot.writeString(struct.eventId);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, ack_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.client = new ClientInfo();
          struct.client.read(iprot);
          struct.setClientIsSet(true);
        }
        if (incoming.get(1)) {
          struct.eventId = iprot.readString();
          struct.setEventIdIsSet(true);
        }
      }
    }

  }

  public static class ack_result implements org.apache.thrift.TBase<ack_result, ack_result._Fields>, java.io.Serializable, Cloneable, Comparable<ack_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ack_result");

    private static final org.apache.thrift.protocol.TField E_FIELD_DESC = new org.apache.thrift.protocol.TField("e", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new ack_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new ack_resultTupleSchemeFactory());
    }

    public EventError e; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      E((short)1, "e");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // E
            return E;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.E, new org.apache.thrift.meta_data.FieldMetaData("e", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ack_result.class, metaDataMap);
    }

    public ack_result() {
    }

    public ack_result(
      EventError e)
    {
      this();
      this.e = e;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public ack_result(ack_result other) {
      if (other.isSetE()) {
        this.e = new EventError(other.e);
      }
    }

    public ack_result deepCopy() {
      return new ack_result(this);
    }

    @Override
    public void clear() {
      this.e = null;
    }

    public EventError getE() {
      return this.e;
    }

    public ack_result setE(EventError e) {
      this.e = e;
      return this;
    }

    public void unsetE() {
      this.e = null;
    }

    /** Returns true if field e is set (has been assigned a value) and false otherwise */
    public boolean isSetE() {
      return this.e != null;
    }

    public void setEIsSet(boolean value) {
      if (!value) {
        this.e = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case E:
        if (value == null) {
          unsetE();
        } else {
          setE((EventError)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case E:
        return getE();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case E:
        return isSetE();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof ack_result)
        return this.equals((ack_result)that);
      return false;
    }

    public boolean equals(ack_result that) {
      if (that == null)
        return false;

      boolean this_present_e = true && this.isSetE();
      boolean that_present_e = true && that.isSetE();
      if (this_present_e || that_present_e) {
        if (!(this_present_e && that_present_e))
          return false;
        if (!this.e.equals(that.e))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_e = true && (isSetE());
      list.add(present_e);
      if (present_e)
        list.add(e);

      return list.hashCode();
    }

    @Override
    public int compareTo(ack_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetE()).compareTo(other.isSetE());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetE()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.e, other.e);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("ack_result(");
      boolean first = true;

      sb.append("e:");
      if (this.e == null) {
        sb.append("null");
      } else {
        sb.append(this.e);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class ack_resultStandardSchemeFactory implements SchemeFactory {
      public ack_resultStandardScheme getScheme() {
        return new ack_resultStandardScheme();
      }
    }

    private static class ack_resultStandardScheme extends StandardScheme<ack_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, ack_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // E
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.e = new EventError();
                struct.e.read(iprot);
                struct.setEIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, ack_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.e != null) {
          oprot.writeFieldBegin(E_FIELD_DESC);
          struct.e.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class ack_resultTupleSchemeFactory implements SchemeFactory {
      public ack_resultTupleScheme getScheme() {
        return new ack_resultTupleScheme();
      }
    }

    private static class ack_resultTupleScheme extends TupleScheme<ack_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, ack_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetE()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetE()) {
          struct.e.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, ack_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.e = new EventError();
          struct.e.read(iprot);
          struct.setEIsSet(true);
        }
      }
    }

  }

}