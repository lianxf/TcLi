package eureka.server.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerTwoApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerTwoApp.class,args);
    }
}
