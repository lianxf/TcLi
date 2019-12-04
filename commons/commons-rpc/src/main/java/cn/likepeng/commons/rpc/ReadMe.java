package cn.likepeng.commons.rpc;

public class ReadMe {
    /**
     *  解决springboot启动阻塞
     *  实现 ApplicationListener<ContextRefreshedEvent>  接口
     *
     *  注入启动类
     *  @Autowired
     *  RpcServer rpcServer;
     *
     *  if(event.getApplicationContext().getParent() == null){
     *        new Thread(()->rpcServer.run()).start();
     *  }
     *
     *
     * */

}
