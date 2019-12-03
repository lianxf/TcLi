package eureka.client.two.controller;

import client.wrapper.service.TestService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements TestService {

    public String test(){
        return "我是测试-2  来自客户端-2";
    }
}
