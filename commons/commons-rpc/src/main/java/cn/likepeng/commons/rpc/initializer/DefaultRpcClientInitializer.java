package cn.likepeng.commons.rpc.initializer;

import cn.likepeng.commons.rpc.handler.DefaultRpcClientHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

public class DefaultRpcClientInitializer extends AbstractRpcInitializer{

    @Override
    public void addHeartBeatHandler(ChannelPipeline pipeline) {
        IdleStateHandler idleStateHandler = new IdleStateHandler(0, 5, 0);
        pipeline.addLast(idleStateHandler);
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        DefaultRpcClientHandler defaultRpcClientHandler = new DefaultRpcClientHandler();
        pipeline.addLast(defaultRpcClientHandler);
    }
}
