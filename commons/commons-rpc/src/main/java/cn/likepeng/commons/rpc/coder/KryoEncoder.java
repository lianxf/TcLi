package cn.likepeng.commons.rpc.coder;

import cn.likepeng.commons.rpc.utils.SerializerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out){
        byte[] serialize = SerializerUtil.kryoSerialize(msg);
        out.writeBytes(serialize);
    }
}
