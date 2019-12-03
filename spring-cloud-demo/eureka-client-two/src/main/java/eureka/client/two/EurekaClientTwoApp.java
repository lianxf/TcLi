package eureka.client.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientTwoApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientTwoApp.class,args);
    }
}
