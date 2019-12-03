package shiro.boot.filter;

import commons.core.response.Response;
import commons.core.utils.HttpServletResponUtil;
import commons.core.utils.RespUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.springframework.util.StringUtils;
import shiro.boot.config.EnableShiroSecurity;
import shiro.boot.exception.ShiroAuthenticationException;
import shiro.boot.response.TokenResponse;
import shiro.boot.service.ShiroSecurityService;
import shiro.boot.token.StatelessToken;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StatelessFilter extends AuthenticationFilter {
    private ShiroSecurityService shiroSecurityService;
    private EnableShiroSecurity enableShiroSecurity;

    public StatelessFilter(ShiroSecurityService shiroSecurityService, EnableShiroSecurity enableShiroSecurity) {
        this.shiroSecurityService = shiroSecurityService;
        this.enableShiroSecurity = enableShiroSecurity;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getToken(servletRequest);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (StringUtils.isEmpty(token)) {
            Response errorResp = RespUtil.error(403, "未登录");
            HttpServletResponUtil.responJson(response,errorResp);
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
            TokenResponse tokenResponse = shiroSecurityService.checkAndFreshToken(token);
            Integer tokenStatus = tokenResponse.getCode();
            if (tokenStatus != 200 ){
                throw new ShiroAuthenticationException(tokenResponse.getMsg(),tokenResponse.getCode());
            }
            response.addHeader(enableShiroSecurity.getHeader(),tokenResponse.getData().getToken());
            return true;
        } catch (AuthenticationException e) {
            if (e instanceof ShiroAuthenticationException){
                ShiroAuthenticationException error = (ShiroAuthenticationException) e;
                Response errorResp = RespUtil.error(error.getCode(), error.getMessage());
                try {
                    HttpServletResponUtil.responJson(response,errorResp);
                } catch (IOException ex) {
                    System.out.println("io异常");
                }
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
}
