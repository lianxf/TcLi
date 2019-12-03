package eureka.client.two.controller;

import client.wrapper.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserService {

    @Override
    public String userList(Integer age) throws Exception {
        int i = 1/0;
        return "用户列表-客户端-2";
    }
}
