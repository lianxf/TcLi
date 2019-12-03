package fegin.client.client.hystrix;

import fegin.client.client.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientHystrix implements UserClient {

    @Override
    public String userList(Integer age) throws Exception {
        return "用户列表服务暂时不可用";
    }
}
