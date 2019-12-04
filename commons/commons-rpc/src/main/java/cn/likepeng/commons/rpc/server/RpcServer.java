package cn.likepeng.commons.rpc.server;

import cn.likepeng.commons.rpc.initializer.AbstractRpcInitializer;
import cn.likepeng.commons.rpc.initializer.DefaultRpcServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
public class RpcServer {
    private RpcServerSettings rpcServerSettings = new RpcServerSettings();

    public void run(){
        NioEventLoopGroup bossGroup = rpcServerSettings.bossGroup;
        NioEventLoopGroup workGroup = rpcServerSettings.workGroup;
        Integer port = rpcServerSettings.port;
        Map<ChannelOption, Boolean> channelOptions = rpcServerSettings.getChannelOptions();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class);

            channelOptions.forEach((channelOption, flag) -> bootstrap.childOption(channelOption,flag) );

            bootstrap.childHandler(rpcServerSettings.channelInitializer);

            ChannelFuture sync = bootstrap.bind(port).sync();
            log.info("服务端在"+port+"端口启动");
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    @Data
    public static class RpcServerSettings{
        private Integer port;
        private NioEventLoopGroup bossGroup =  new NioEventLoopGroup(1);
        private NioEventLoopGroup workGroup;
        private Map<ChannelOption,Boolean> channelOptions;
        private AbstractRpcInitializer channelInitializer;

        public RpcServerSettings() {
            this.port = 8084;
            this.workGroup = new NioEventLoopGroup();
            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.TCP_NODELAY,true);
            options.put(ChannelOption.SO_KEEPALIVE,true);
            this.channelOptions = options;
            this.channelInitializer = new DefaultRpcServerInitializer();
        }

        public RpcServerSettings(Integer port) {
            this.port = port;
            this.workGroup = new NioEventLoopGroup();
            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.TCP_NODELAY,true);
            options.put(ChannelOption.SO_KEEPALIVE,true);
            this.channelOptions = options;
            this.channelInitializer = new DefaultRpcServerInitializer();
        }

        public RpcServerSettings(Integer port, NioEventLoopGroup workGroup) {
            this.port = port;
            this.workGroup = workGroup;
            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.TCP_NODELAY,true);
            options.put(ChannelOption.SO_KEEPALIVE,true);
            this.channelOptions = options;
            this.channelInitializer = new DefaultRpcServerInitializer();
        }

        public RpcServerSettings(Integer port, NioEventLoopGroup workGroup, Map<ChannelOption, Boolean> channelOptions) {
            this.port = port;
            this.workGroup = workGroup;
            this.channelOptions = channelOptions;
            this.channelInitializer = new DefaultRpcServerInitializer();
        }

        public RpcServerSettings(Integer port, NioEventLoopGroup workGroup, Map<ChannelOption, Boolean> channelOptions, AbstractRpcInitializer channelInitializer) {
            this.port = port;
            this.workGroup = workGroup;
            this.channelOptions = channelOptions;
            this.channelInitializer = channelInitializer;
        }

        public RpcServerSettings(Integer port, AbstractRpcInitializer channelInitializer) {
            this.port = port;
            this.workGroup = new NioEventLoopGroup();
            Map<ChannelOption,Boolean> options =  new HashMap<>();
            options.put(ChannelOption.TCP_NODELAY,true);
            options.put(ChannelOption.SO_KEEPALIVE,true);
            this.channelOptions = options;
            this.channelInitializer = channelInitializer;
        }

        public RpcServerSettings(Integer port, Map<ChannelOption, Boolean> channelOptions) {
            this.port = port;
            this.workGroup = new NioEventLoopGroup();
            this.channelOptions = channelOptions;
            this.channelInitializer = channelInitializer;
        }

        public RpcServerSettings(Integer port, Map<ChannelOption, Boolean> channelOptions, AbstractRpcInitializer channelInitializer) {
            this.port = port;
            this.workGroup = new NioEventLoopGroup();
            this.channelOptions = channelOptions;
            this.channelInitializer = channelInitializer;
        }
    }

    public void close(){
        if (!rpcServerSettings.bossGroup.isShuttingDown()){
            rpcServerSettings.bossGroup.shutdownGracefully();
            rpcServerSettings.workGroup.shutdownGracefully();
        }
    }
}
