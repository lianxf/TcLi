package commons.boot.request;

import cn.hutool.core.date.DateUtil;
import cn.likepeng.commons.core.enumeration.TimePattern;
import cn.likepeng.commons.core.utils.HttpServletRequestUtil;
import cn.likepeng.commons.core.utils.IPUtil;
import cn.likepeng.commons.core.utils.TimeUtil;
import cn.likepeng.commons.core.utils.gmap.IPInfo;
import cn.likepeng.commons.core.utils.servlet.UserAgent;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public class DefaultSaveRequestInfo extends SaveRequestInfo {

    @Override
    public synchronized Object saveLog(HttpServletRequest request, MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        String requestedSessionId = request.getRequestedSessionId();
        String requestMethod = request.getMethod();
        String queryString = request.getQueryString();
        String path = request.getRequestURI();
        String remoteIP = HttpServletRequestUtil.getRemoteIP(request);
        IPInfo ipInfo = IPUtil.ipInfo(remoteIP);
        String province = ipInfo.getProvince();
        String city = ipInfo.getCity();
        String rectangle = ipInfo.getRectangle();


        UserAgent osInfo = HttpServletRequestUtil.getOSInfo(request);
        String browser = osInfo.getBrowser().getName();
        String operatingSystem = osInfo.getOperatingSystem().getName();


        Date startTime = new Date();
        String startTimeString = TimeUtil.getDateString(startTime, TimePattern.MILLISECOND_PATTERN);

        Object proceed = invocation.proceed();

        Date endTime = new Date();
        String executionTime  = "执行用时"+DateUtil.betweenMs(startTime, endTime)+"毫秒";

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setSessionId(requestedSessionId);
        requestInfo.setRequestMethod(requestMethod);
        requestInfo.setClientIp(remoteIP);
        requestInfo.setPath(path);
        requestInfo.setMethod(methodName);
        requestInfo.setParams(queryString);
        requestInfo.setResponse(proceed);
        requestInfo.setStartTime(startTimeString);
        requestInfo.setExecutionTime(executionTime);
        requestInfo.setClientBrowser(browser);
        requestInfo.setClientOperatingSystem(operatingSystem);
        requestInfo.setClientIpProvince(province);
        requestInfo.setClientIpCity(city);
        requestInfo.setClientIpRectangle(rectangle);
        log.info(JSON.toJSONString(requestInfo));
        return proceed;
    }
}
