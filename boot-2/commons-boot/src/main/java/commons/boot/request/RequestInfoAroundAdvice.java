package commons.boot.request;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;

public class RequestInfoAroundAdvice implements MethodInterceptor {

    @Autowired(required = false)
    HttpServletRequest request;

    @Autowired(required = false)
    EnableSaveRequestInfo enableSaveRequestInfo;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return enableSaveRequestInfo.getSaveRequestInfo().saveLog(request,invocation);
    }
}
