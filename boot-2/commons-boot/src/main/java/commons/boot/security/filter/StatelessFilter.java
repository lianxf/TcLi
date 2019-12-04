package commons.boot.security.filter;

import cn.likepeng.commons.core.response.Response;
import cn.likepeng.commons.core.utils.RespUtil;
import com.alibaba.fastjson.JSON;
import commons.boot.enumeration.TokenStatus;
import commons.boot.security.EnableShiroSecurity;
import commons.boot.security.exception.ShiroAuthenticationException;
import commons.boot.security.model.CheckToken;
import commons.boot.security.service.SecurityTokenService;
import commons.boot.security.token.StatelessToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.springframework.util.StringUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class StatelessFilter extends AuthenticationFilter {
    private SecurityTokenService securityTokenService;
    private EnableShiroSecurity enableShiroSecurity;

    public StatelessFilter(SecurityTokenService securityTokenService,EnableShiroSecurity enableShiroSecurity) {
        this.securityTokenService = securityTokenService;
        this.enableShiroSecurity = enableShiroSecurity;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getToken(servletRequest);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (StringUtils.isEmpty(token)) {
            responseBody("未登录，请登录",401, response);
            return false;
        } else {
            boolean isSuccess = this.login(token,response);
            return isSuccess;
        }
    }

    private boolean login(String token, HttpServletResponse response) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new StatelessToken(token));
            CheckToken checkToken = securityTokenService.checkAndFreshToken(token);
            TokenStatus tokenStatus = checkToken.getTokenStatus();
            if (!tokenStatus.equals(TokenStatus.NORMAL)){
                throw new ShiroAuthenticationException(tokenStatus.getStateDesc(),tokenStatus.getState());
            }
            response.setHeader(enableShiroSecurity.getHeader(),checkToken.getToken());
            return true;
        } catch (AuthenticationException e) {
            if (e instanceof ShiroAuthenticationException){
                ShiroAuthenticationException error = (ShiroAuthenticationException) e;
                responseBody(error.getMessage(),error.getCode(),response);
            }
            return false;
        }
    }

    private String getToken(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorizationHeader = request.getHeader(enableShiroSecurity.getHeader());//获取请求头中的Authorization属性
        if (!StringUtils.isEmpty(authorizationHeader)) {
            return authorizationHeader.replace(" ", "");
        }
        return null;
    }

    private void responseBody(String msg,Integer status, HttpServletResponse response) {

        response.setContentType("application/json;charset=UTF-8");
        Response error = RespUtil.error(status, msg);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(error));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                writer.close();
            }
        }
    }
}
