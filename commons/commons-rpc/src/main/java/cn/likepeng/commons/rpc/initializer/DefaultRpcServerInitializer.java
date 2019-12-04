package cn.likepeng.commons.rpc.initializer;

import cn.likepeng.commons.rpc.handler.DefaultRpcServerHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

public class DefaultRpcServerInitializer extends AbstractRpcInitializer{

    @Override
    public void addHeartBeatHandler(ChannelPipeline pipeline) {
        pipeline.addLast(new IdleStateHandler(6, 0, 0));
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        DefaultRpcServerHandler defaultRpcServerHandler = new DefaultRpcServerHandler();
        pipeline.addLast(defaultRpcServerHandler);
    }
}
