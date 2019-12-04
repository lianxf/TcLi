package cn.likepeng.commons.rpc.client;

import cn.likepeng.commons.rpc.server.RpcServer;
import org.junit.Test;
import static org.junit.Assert.*;

public class RpcClientTest {

    @Test
    public void server() {
        RpcServer rpcServer = new RpcServer();
        rpcServer.run();
    }

    @Test
    public void client() {
        RpcClient rpcClient = new RpcClient();
        rpcClient.run();
    }
}