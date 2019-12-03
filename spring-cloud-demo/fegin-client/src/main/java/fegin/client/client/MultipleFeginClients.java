package fegin.client.client;

import fegin.client.client.hystrix.TestFeginClientHystrix;
import fegin.client.client.hystrix.UserFeginClientHystrix;
import feign.Client;
import feign.Contract;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Import(FeignClientsConfiguration.class)
@Service
public class MultipleFeginClients {

    private TestFeginClient testFeginClient;

    private UserFeginClient userFeginClient;

    @Autowired
    public MultipleFeginClients(Decoder decoder, Encoder encoder, Client client, Contract contract){

        TestFeginClient testFeginClient = HystrixFeign.builder()
                .client(client)
                .decoder(decoder)
                .contract(contract)
                .target(TestFeginClient.class, "http://eureka-client-one", new TestFeginClientHystrix());

        UserFeginClient userFeginClient = HystrixFeign.builder()
                .client(client)
                .decoder(decoder)
                .contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(UserFeginClient.class, "http://eureka-client-one", new UserFeginClientHystrix());

        this.testFeginClient = testFeginClient;
        this.userFeginClient = userFeginClient;
    }



    public TestFeginClient getTestFeginClient() {
        return testFeginClient;
    }

    public UserFeginClient getUserFeginClient() {
        return userFeginClient;
    }
}
