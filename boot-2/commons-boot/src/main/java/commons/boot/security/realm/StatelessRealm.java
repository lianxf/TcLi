package commons.boot.security.realm;

import commons.boot.security.exception.ShiroAuthenticationException;
import commons.boot.security.model.Token;
import commons.boot.security.token.StatelessToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public class StatelessRealm extends AuthorizingRealm {
    @Autowired(required = false)
    RedissonClient securityTokenRedisClient;

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

        RBucket<Token> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        if (tokenRBucket.isExists()){
            Token token = tokenRBucket.get();
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(tokenString, tokenString, this.getName());
            return authenticationInfo;
        }
        throw new ShiroAuthenticationException("Token不正确",506);
    }

    //授权 Authorization
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String tokenString = (String) principal.getPrimaryPrincipal();
        RBucket<Token> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        if (tokenRBucket.isExists()){
            Token token = tokenRBucket.get();
            List<String> roles = token.getRoles();
            List<String> permissions = token.getPermissions();

            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRoles(roles);
            authorizationInfo.addStringPermissions(permissions);

            return authorizationInfo;
        }
        return null;
    }
}
