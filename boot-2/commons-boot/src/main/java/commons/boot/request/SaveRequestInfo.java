package commons.boot.request;

import org.aopalliance.intercept.MethodInvocation;
import javax.servlet.http.HttpServletRequest;

public abstract class SaveRequestInfo {
    public abstract Object saveLog(HttpServletRequest request, MethodInvocation invocation)throws Throwable;
}
