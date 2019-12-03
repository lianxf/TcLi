package fegin.client.controller;

import fegin.client.client.MultipleFeginClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("multiple")
public class MultipleFeginController {

    @Autowired
    MultipleFeginClients multipleFeginClients;

    @GetMapping("/test")
    public Object test()throws Exception{
        String test = multipleFeginClients.getTestFeginClient().test();
        return test;
    }

    @GetMapping("/user")
    public Object user()throws Exception{
        String userList = multipleFeginClients.getUserFeginClient().userList(1);
        return userList;
    }

}
