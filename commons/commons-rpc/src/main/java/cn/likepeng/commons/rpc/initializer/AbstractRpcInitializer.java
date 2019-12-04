package cn.likepeng.commons.rpc.initializer;

import cn.likepeng.commons.rpc.coder.ProtostuffDecoder;
import cn.likepeng.commons.rpc.coder.ProtostuffEncoder;
import cn.likepeng.commons.rpc.protocol.RpcMessage;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public abstract class AbstractRpcInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtostuffDecoder(RpcMessage.class));
        pipeline.addLast(new ProtostuffEncoder());
        addHeartBeatHandler(pipeline);
        addHandler(pipeline);
    }

    public abstract void addHeartBeatHandler(ChannelPipeline pipeline);

    public abstract void addHandler(ChannelPipeline pipeline);
}
