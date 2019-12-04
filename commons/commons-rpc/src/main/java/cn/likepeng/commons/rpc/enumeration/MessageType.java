package cn.likepeng.commons.rpc.enumeration;

import lombok.Getter;

/**
 * 消息类型
 * 0.业务请求消息
 * 1.业务响应消息
 * 2.业务请求响应消息
 * 3.握手请求消息
 * 4.握手应答消息
 * 5.心跳请求消息
 * 6.心跳应答消息
 *
 */

@Getter
public enum MessageType {
    BUSINESS_REQUEST((byte)0),
    BUSINESS_RESPONSE((byte)1),
    SHAKE_HANDS_REQUEST((byte)3),
    SHAKE_HANDS_RESPONSE((byte)4),
    HEART_BEAT_REQUEST((byte)5),
    HEART_BEAT_RESPONSE((byte)6);

    private Byte type;

    MessageType(Byte type) {
        this.type = type;
    }
}
