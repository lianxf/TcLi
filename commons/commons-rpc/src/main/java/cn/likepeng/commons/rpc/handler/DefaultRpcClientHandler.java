package cn.likepeng.commons.rpc.handler;

import cn.likepeng.commons.rpc.client.RpcClient;
import cn.likepeng.commons.rpc.protocol.RpcMessage;
import cn.likepeng.commons.rpc.protocol.RpcMessageFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultRpcClientHandler extends AbstractHandler {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String id = ctx.channel().id().asLongText();
        log.error("客户端"+id+"\t10s 之后尝试重新连接服务器...");
        Thread.sleep(10 * 1000);
        new RpcClient().run();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.WRITER_IDLE.equals(event.state())) {
                sendHeartbeatPacket(ctx);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String id = ctx.channel().id().asLongText();
        log.error("客户端"+id+"出现异常:"+cause.getMessage());
    }

    private void sendHeartbeatPacket(ChannelHandlerContext ctx) {
        RpcMessage rpcMessage = RpcMessageFactory.protoStuffHeartbeatRequestMessage();
        ctx.writeAndFlush(rpcMessage);
    }

    @Override
    public void handlerData(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof RpcMessage) {
            RpcMessage packet = (RpcMessage) msg;
            log.info("heart_beat_response: "+packet.getBody().toString());
        }
    }
}
