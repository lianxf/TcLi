package commons.boot.request;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EnableSaveRequestInfo.class)
public class SaveRequestInfoConfig {

    @Autowired(required = false)
    EnableSaveRequestInfo enableSaveRequestInfo;

    @Bean
    public RequestInfoAroundAdvice requestInfoAroundAdvice(){
        RequestInfoAroundAdvice requestInfoAroundAdvice = new RequestInfoAroundAdvice();
        return requestInfoAroundAdvice;
    }

    @Bean
    public AspectJExpressionPointcutAdvisor saveRequestInfoAdvisor(RequestInfoAroundAdvice requestInfoAroundAdvice){
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(enableSaveRequestInfo.getExpression());
        advisor.setAdvice(requestInfoAroundAdvice);
        return advisor;
    }
}
