package client.wrapper.service;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {

    @GetMapping(value = "user",consumes = "application/json")
    String userList(@RequestParam(defaultValue = "1") Integer age)throws Exception;
}
