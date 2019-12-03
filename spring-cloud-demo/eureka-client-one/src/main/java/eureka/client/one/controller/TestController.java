package eureka.client.one.controller;

import client.wrapper.service.TestService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements TestService {

    public String test(){
        int i = 1/0;
        return "我是测试-1  来自客户端-1";
    }
}
