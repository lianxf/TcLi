package eureka.client.one.controller;

import client.wrapper.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserService {

    @Override
    public String userList(Integer age) throws Exception {
        return "用户列表-客户端-1";
    }
}
