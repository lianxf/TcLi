package cn.likepeng.commons.rpc.client;

import cn.likepeng.commons.rpc.initializer.AbstractRpcInitializer;
import cn.likepeng.commons.rpc.initializer.DefaultRpcClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class RpcClient {
    private RpcClientSettings rpcClientSetting = new RpcClientSettings();

    public void run(){
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(rpcClientSetting.loopGroup)
                    .channel(NioSocketChannel.class);

            Map<ChannelOption, Boolean> channelOptions = rpcClientSetting.channelOptions;
            channelOptions.forEach((channelOption, flag) -> bootstrap.option(channelOption,flag) );
            bootstrap.handler(rpcClientSetting.channelInitializer);
            ChannelFuture channelFuture = bootstrap.connect(rpcClientSetting.host, rpcClientSetting.port).sync();
            ChannelId id = channelFuture.channel().id();
            log.info("客户端"+id+"与"+rpcClientSetting.host+":"+rpcClientSetting.port+"服务端建立连接");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            rpcClientSetting.loopGroup.shutdownGracefully();
        }
    }

    @Data
    public static class RpcClientSettings{
        private  String  host;
        private  Integer port;
        private  NioEventLoopGroup loopGroup;
        private  Map<ChannelOption,Boolean> channelOptions;
        private  AbstractRpcInitializer channelInitializer;

        public RpcClientSettings() {
            this.host = "127.0.0.1";
            this.port = 8084;
            this.loopGroup = new NioEventLoopGroup();

            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.SO_KEEPALIVE,true);
            this.channelOptions = options;
            this.channelInitializer = new DefaultRpcClientInitializer();
        }

        public RpcClientSettings(String host, Integer port) {
            this.host = host;
            this.port = port;

            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.SO_KEEPALIVE,true);
            this.channelOptions = options;
            this.channelInitializer = new DefaultRpcClientInitializer();
        }

        public RpcClientSettings(String host, Integer port, AbstractRpcInitializer channelInitializer) {
            this.host = host;
            this.port = port;
            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.SO_KEEPALIVE,true);
            this.channelOptions = options;
            this.channelInitializer = channelInitializer;
        }

        public RpcClientSettings(String host, Integer port, AbstractRpcInitializer channelInitializer, NioEventLoopGroup loopGroup) {
            this.host = host;
            this.port = port;
            this.loopGroup = loopGroup;
            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.TCP_NODELAY,true);
            this.channelOptions = options;
            this.channelInitializer = channelInitializer;
        }

        public RpcClientSettings(String host, Integer port, AbstractRpcInitializer channelInitializer, NioEventLoopGroup loopGroup, Map<ChannelOption, Boolean> channelOptions) {
            this.host = host;
            this.port = port;
            this.channelInitializer = channelInitializer;
            this.loopGroup = loopGroup;
            this.channelOptions = channelOptions;
        }
    }

    public void close(){
        if (!rpcClientSetting.loopGroup.isShuttingDown()){
            rpcClientSetting.loopGroup.shutdownGracefully();
        }
    }
}
