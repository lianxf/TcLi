package cn.likepeng.commons.rpc.handler;

import cn.likepeng.commons.rpc.protocol.Header;
import cn.likepeng.commons.rpc.protocol.RpcMessage;
import cn.likepeng.commons.rpc.protocol.RpcMessageFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultRpcServerHandler extends AbstractHandler {

    // 心跳丢失计数器
    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String id = ctx.channel().id().asLongText();
        log.info("客户端"+id+"建立连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String id = ctx.channel().id().asLongText();
        log.error("客户端"+id+"断开连接");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (IdleState.READER_IDLE .equals(idleStateEvent.state()) ){
                String id = ctx.channel().id().asLongText();
                if (counter >= 3) {
                    ctx.channel().close().sync();
                    log.error("客户端"+id+"断开连接");
                } else {
                    counter++;
                    log.error("客户端"+id+"丢失了第"+counter + " 个心跳包");
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String id = ctx.channel().id().asLongText();
       log.error("客户端"+id+"出现异常:"+cause.getMessage());
    }

    private void handleHeartbeat(ChannelHandlerContext ctx, RpcMessage packet) {
        // 将心跳丢失计数器置为0
        counter = 0;
        log.info("heart_beat_request: "+packet.getBody().toString());
        ReferenceCountUtil.release(packet);

        RpcMessage rpcMessage = RpcMessageFactory.protoStuffHeartbeatResponseMessage();
        ctx.writeAndFlush(rpcMessage);
    }

    private void handleData(ChannelHandlerContext ctx, RpcMessage packet) {
        // 将心跳丢失计数器置为0
        counter = 0;
        Object data = packet.getBody();
        System.out.println(data);
        ReferenceCountUtil.release(packet);
    }

    @Override
    public void handlerData(ChannelHandlerContext ctx, Object msg) {
        // 判断接收到的包类型
        if (msg instanceof RpcMessage) {
            RpcMessage packet = (RpcMessage) msg;

            Header header = packet.getHeader();
            Byte type = header.getType();

            switch (type) {
                case 0:
                    handleData(ctx, packet);
                    break;

                case 5:
                    handleHeartbeat(ctx, packet);
                    break;
                default:
                    break;
            }
        }
    }
}
