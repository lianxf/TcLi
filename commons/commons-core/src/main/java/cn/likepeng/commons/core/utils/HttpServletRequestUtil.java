package cn.likepeng.commons.core.utils;

import cn.hutool.core.net.NetUtil;
import cn.likepeng.commons.core.utils.servlet.UserAgent;
import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil extends NetUtil {

    public static String getLocalIP(){
        return NetUtil.getLocalhostStr();
    }

    public static String getRemoteIP(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            String remoteAddr = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(remoteAddr)){
                ip = getLocalIP();
            }
            else ip = remoteAddr;
        }
        return ip;
    }

    public static UserAgent getOSInfo(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent;
    }
}
