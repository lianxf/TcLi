package cn.likepeng.commons.rpc.protocol;

import cn.likepeng.commons.rpc.enumeration.MessageType;
import cn.likepeng.commons.rpc.utils.IdUtil;
import cn.likepeng.commons.rpc.utils.SerializerUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@SuppressWarnings("all")
public class RpcMessageFactory {

    public static RpcMessage protoStuffRequestMessage(Object data){
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.BUSINESS_REQUEST.getType(), IdUtil.objectId(), (byte)1, null);
        return rpcMessage;
    }

    public static RpcMessage protoStuffResponMessage(Object data){
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.BUSINESS_RESPONSE.getType(), IdUtil.objectId(), (byte)1, null);
        return rpcMessage;
    }

    public static RpcMessage protoStuffHeartbeatRequestMessage(String data){
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_REQUEST.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage protoStuffHeartbeatRequestMessage(){
        String data = "c-> " + nowDate();
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_REQUEST.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage protoStuffHeartbeatResponseMessage(String data){
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_RESPONSE.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage protoStuffHeartbeatResponseMessage(){
        String data = "s-> " + nowDate();
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_RESPONSE.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage protoStuffShakeHandsRequestMessage(String data){
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.SHAKE_HANDS_REQUEST.getType(), IdUtil.objectId(), (byte)3, null);
        return rpcMessage;
    }

    public static RpcMessage protoStuffShakeHandsResponMessage(String data){
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.SHAKE_HANDS_RESPONSE.getType(), IdUtil.objectId(), (byte)3, null);
        return rpcMessage;
    }
    

    public static RpcMessage protoStuffMessage(Object data, Integer crCode, String sessionId,MessageType messageType, Byte priority, Map<String,Object> attachment){
        byte[] bytes = SerializerUtil.protoStuffSerialize(data);
        RpcMessage rpcMessage = instance(data, crCode, bytes.length, messageType.getType(), sessionId, priority, attachment);
        return rpcMessage;
    }

    public static RpcMessage kryoMessage(Object data, MessageType messageType, Integer crCode, String sessionId, Byte priority, Map<String,Object> attachment){
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, crCode, bytes.length, messageType.getType(), sessionId, priority, attachment);
        return rpcMessage;
    }

    public static RpcMessage kryoRequestMessage(Object data){
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.BUSINESS_REQUEST.getType(), IdUtil.objectId(), (byte)1, null);
        return rpcMessage;
    }

    public static RpcMessage kryoResponseMessage(Object data){
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.BUSINESS_RESPONSE.getType(), IdUtil.objectId(), (byte)1, null);
        return rpcMessage;
    }

    public static RpcMessage kryoHeartbeatRequestMessage(String data){
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_REQUEST.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage kryoHeartbeatRequestMessage(){
        String data = "c-> " + nowDate();
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_REQUEST.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage kryoHeartbeatResponseMessage(String data){
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_RESPONSE.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage kryoHeartbeatResponseMessage(){
        String data = "s-> " + nowDate();
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.HEART_BEAT_RESPONSE.getType(), IdUtil.objectId(), (byte)2, null);
        return rpcMessage;
    }

    public static RpcMessage kryoShakeHandsRequestMessage(String data){
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.SHAKE_HANDS_REQUEST.getType(), IdUtil.objectId(), (byte)3, null);
        return rpcMessage;
    }

    public static RpcMessage kryoShakeHandsResponMessage(String data){
        byte[] bytes = SerializerUtil.kryoSerialize(data);
        RpcMessage rpcMessage = instance(data, 0xABEF0101, bytes.length, MessageType.SHAKE_HANDS_RESPONSE.getType(), IdUtil.objectId(), (byte)3, null);
        return rpcMessage;
    }

    private static RpcMessage instance(Object data, Integer crCode,Integer length,byte messageType, String sessionId, Byte priority, Map<String,Object> attachment){
        Header header = new Header(crCode,length,sessionId,messageType,priority,attachment);
        header.setCrCode(crCode);
        RpcMessage rpcMessage = new RpcMessage();
        rpcMessage.setHeader(header);
        rpcMessage.setBody(data);
        return rpcMessage;
    }

    private static String nowDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(date);
        return now;
    }
}
