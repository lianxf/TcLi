package shiro.boot.realm;

import commons.core.utils.ValidateUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import shiro.boot.entity.RedisToken;
import shiro.boot.exception.ShiroAuthenticationException;
import shiro.boot.response.TokenResponse;
import shiro.boot.service.ShiroSecurityService;
import shiro.boot.token.StatelessToken;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
public class StatelessRealm extends AuthorizingRealm {
    @Autowired(required = false)
    ShiroSecurityService shiroSecurityService;

    public String getName() {
        return "Realm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && StatelessToken.class.isAssignableFrom(token.getClass());
    }

    //认证 Authentication
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) authenticationToken;
        String tokenString = statelessToken.getToken();

        RedisToken tokenInfo = shiroSecurityService.tokenInfo(tokenString);
        if (!ValidateUtil.isEmpty(tokenInfo)){
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(tokenString, tokenString, this.getName());
            return authenticationInfo;
        }
        throw new ShiroAuthenticationException("无效Token",506);
    }

    //授权 Authorization
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String tokenString = (String) principal.getPrimaryPrincipal();
        RedisToken redisToken = shiroSecurityService.tokenInfo(tokenString);

        if (!ValidateUtil.isEmpty(redisToken)){
            List<String> roles = redisToken.getRoles();
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRoles(roles);
            authorizationInfo.addStringPermissions(Collections.emptyList());
            return authorizationInfo;
        }
        return null;
    }
}
