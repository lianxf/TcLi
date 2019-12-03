package eureka.client.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientOneApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientOneApp.class,args);
    }
}
