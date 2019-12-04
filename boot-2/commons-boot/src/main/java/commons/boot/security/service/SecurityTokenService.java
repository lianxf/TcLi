package commons.boot.security.service;

import cn.likepeng.commons.core.utils.TokenUtil;
import commons.boot.enumeration.TokenStatus;
import commons.boot.security.EnableShiroSecurity;
import commons.boot.security.model.CheckToken;
import commons.boot.security.model.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class SecurityTokenService {

    RedissonClient securityTokenRedisClient;
    EnableShiroSecurity enableShiroSecurity;


    public SecurityTokenService(RedissonClient securityTokenRedisClient, EnableShiroSecurity enableShiroSecurity) {
        this.securityTokenRedisClient = securityTokenRedisClient;
        this.enableShiroSecurity = enableShiroSecurity;
    }

    public Token generateToken(String userId, List<String> roles, List<String> permissions){
        String tokenString = TokenUtil.token(userId, enableShiroSecurity.getTokenSalt()+new Date().getTime());
        Integer tokenLiveHours = enableShiroSecurity.getTokenLiveHours();
        RBucket<Token> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);

        boolean exists = tokenRBucket.isExists();

        Token token = new Token();

        if (!exists){
            token.setUserId(Long.parseLong(userId));
            token.setToken(tokenString);
            token.setRoles(roles);
            token.setCreateTime(new Date().getTime());
            token.setPermissions(permissions);
            tokenRBucket.set(token,tokenLiveHours* cn.likepeng.commons.core.enumeration.TimeUnit.HOURS.getSeconds()*1000, TimeUnit.MILLISECONDS);
        }else {
            token = tokenRBucket.get();
        }
        return token;
    }

    public Token generateToken(Long userId, List<String> roles,List<String> permissions){
        return generateToken(userId+"",roles,permissions);
    }

    public CheckToken checkAndFreshToken(String tokenString){
        Integer tokenLiveHours = enableShiroSecurity.getTokenLiveHours();
        RBucket<Token> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);

        CheckToken checkToken = new CheckToken();

        boolean exists = tokenRBucket.isExists();
        if (!exists){
            checkToken.setToken(tokenString);
            checkToken.setTokenStatus(TokenStatus.INVALID);
        }
        else {
            long now = new Date().getTime();

            long remainTimeToLive = tokenRBucket.remainTimeToLive();
            Token token1 = tokenRBucket.get();
            Long tokenCreateTime = token1.getCreateTime();

            if (tokenCreateTime + remainTimeToLive -now < tokenLiveHours* cn.likepeng.commons.core.enumeration.TimeUnit.HOURS.getSeconds()*200){
                checkToken.setToken(tokenString);
                checkToken.setTokenStatus(TokenStatus.EXPIRE);
                return checkToken;
            }
            else {
                Token token = tokenRBucket.get();
                Long createTime = token.getCreateTime();
                if (now-createTime> enableShiroSecurity.getFreshTokenIntervalMinutes()* cn.likepeng.commons.core.enumeration.TimeUnit.MINUTE.getSeconds()*1000){
                    Long userId = token.getUserId();
                    List<String> roles = token.getRoles();
                    List<String> permissions = token.getPermissions();
                    tokenRBucket.delete();
                    Token freshToken = generateToken(userId, roles, permissions);
                    freshToken.getToken();
                    checkToken.setToken(freshToken.getToken());
                    checkToken.setTokenStatus(TokenStatus.NORMAL);
                    return checkToken;
                }else {
                    checkToken.setToken(tokenString);
                    checkToken.setTokenStatus(TokenStatus.NORMAL);
                    return checkToken;
                }
            }
        }
        return checkToken;
    }

    public void removeToken(String tokenString){
        RBucket<Token> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        tokenRBucket.delete();
    }

    public Token tokenInfo(String tokenString){
        RBucket<Token> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        Token token = tokenRBucket.get();
        return token;
    }

    public void loginOut(String tokenString){
        Subject subject = SecurityUtils.getSubject();
        removeToken(tokenString);
        subject.logout();
    }
}
