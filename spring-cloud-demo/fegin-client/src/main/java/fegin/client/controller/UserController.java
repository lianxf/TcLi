package fegin.client.controller;

import fegin.client.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserClient userClient;

    @GetMapping("")
    public Object userList()throws Exception{
        String s = userClient.userList(1);
        return s;
    }
}
