package fegin.client.client;

import client.wrapper.service.UserService;
import fegin.client.client.hystrix.UserClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "eureka-client-one",fallback = UserClientHystrix.class)
public interface UserClient extends UserService {
}
