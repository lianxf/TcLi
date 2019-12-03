package eureka.server.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerOneApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerOneApp.class,args);
    }
}
