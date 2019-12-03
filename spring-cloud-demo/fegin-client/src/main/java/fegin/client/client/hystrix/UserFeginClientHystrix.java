package fegin.client.client.hystrix;

import fegin.client.client.UserFeginClient;

public class UserFeginClientHystrix implements UserFeginClient {

    @Override
    public String userList(Integer age) throws Exception {
        return "user服务暂时不可用";
    }
}
