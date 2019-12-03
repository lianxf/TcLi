package fegin.client.client.hystrix;

import fegin.client.client.TestFeginClient;

public class TestFeginClientHystrix implements TestFeginClient {
    @Override
    public String test() throws Exception {
        return "test服务暂时不可用";
    }
}
