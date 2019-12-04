package cn.likepeng.commons.rpc.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        handlerData(ctx,msg);
    }

    public abstract void handlerData(ChannelHandlerContext ctx, Object msg);
}
