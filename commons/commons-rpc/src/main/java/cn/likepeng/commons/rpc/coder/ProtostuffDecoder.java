package cn.likepeng.commons.rpc.coder;

import cn.likepeng.commons.rpc.utils.SerializerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class ProtostuffDecoder extends ByteToMessageDecoder {

    private Class<?> clazz;

    public ProtostuffDecoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out){
        int length  = in.readableBytes();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        Object deserialize = SerializerUtil.protoStuffDeserialize(bytes, this.clazz);
        out.add(deserialize);
    }
}
