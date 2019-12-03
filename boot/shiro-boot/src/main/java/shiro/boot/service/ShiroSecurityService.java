package shiro.boot.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import commons.core.utils.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import shiro.boot.config.EnableShiroSecurity;
import shiro.boot.entity.RedisToken;
import shiro.boot.response.TokenResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class ShiroSecurityService {

    RedissonClient securityTokenRedisClient;
    EnableShiroSecurity enableShiroSecurity;


    public ShiroSecurityService(RedissonClient securityTokenRedisClient, EnableShiroSecurity enableShiroSecurity) {
        this.securityTokenRedisClient = securityTokenRedisClient;
        this.enableShiroSecurity = enableShiroSecurity;
    }

    public TokenResponse generateToken(String userId, List<String> roles){
        long tokenLiveHours = enableShiroSecurity.getTokenLiveHours();
        String tokenSecret = enableShiroSecurity.getTokenSecret();


        Date createTime = new Date();
        String tokenStr = SecurityUtil.HmacSHA256(userId + createTime.getTime(), tokenSecret);

        RBucket<RedisToken> tokenRBucket = securityTokenRedisClient.getBucket(tokenStr);

        RedisToken redisToken = new RedisToken();
        redisToken.setUserId(userId);
        redisToken.setRoles(roles);
        redisToken.setCreateTime(createTime);
        redisToken.setToken(tokenStr);

        tokenRBucket.set(redisToken,tokenLiveHours+2, TimeUnit.HOURS);

        TokenResponse token = new TokenResponse();
        token.setData(redisToken);
        return token;
    }

    public TokenResponse checkAndFreshToken(String tokenString){
        RBucket<RedisToken> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        long tokenLiveHours = enableShiroSecurity.getTokenLiveHours();
        long freshTokenIntervalMinutes = enableShiroSecurity.getFreshTokenIntervalMinutes();


        TokenResponse tokenResponse = new TokenResponse();
        if (!tokenRBucket.isExists()){
            tokenResponse.setCode(506);
            tokenResponse.setMsg("无效Token");
            return tokenResponse;
        }
        RedisToken redisToken = tokenRBucket.get();
        Date createTime = redisToken.getCreateTime();
        Date now = new Date();
        long betweenHours = DateUtil.between(createTime, now, DateUnit.HOUR,true);
        long betweenMinuts = DateUtil.between(createTime, now, DateUnit.MINUTE,true);

        if (betweenHours > tokenLiveHours){
            TokenResponse freshTokenResponse = generateToken(redisToken.getUserId(), redisToken.getRoles());
            RedisToken freshToken = freshTokenResponse.getData();
            tokenRBucket.delete();
            tokenResponse.setData(freshToken);
            return tokenResponse;
        }
        if (betweenMinuts > freshTokenIntervalMinutes){
            TokenResponse freshTokenResponse = generateToken(redisToken.getUserId(), redisToken.getRoles());
            RedisToken freshToken = freshTokenResponse.getData();
            tokenRBucket.delete();
            tokenResponse.setData(freshToken);
            return tokenResponse;
        }
        tokenResponse.setData(redisToken);
        return tokenResponse;
    }

    public TokenResponse checkToken(String tokenString){
        RBucket<RedisToken> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        long tokenLiveHours = enableShiroSecurity.getTokenLiveHours();

        TokenResponse tokenResponse = new TokenResponse();
        if (!tokenRBucket.isExists()){
            tokenResponse.setCode(506);
            tokenResponse.setMsg("无效Token");
            return tokenResponse;
        }
        RedisToken redisToken = tokenRBucket.get();
        Date createTime = redisToken.getCreateTime();
        Date now = new Date();
        long betweenHours = DateUtil.between(createTime, now, DateUnit.HOUR,true);

        if (betweenHours > tokenLiveHours){
            tokenResponse.setCode(507);
            tokenResponse.setMsg("Token已失效");
            return tokenResponse;
        }
        tokenResponse.setData(redisToken);
        return tokenResponse;
    }

    public void removeToken(String tokenString){
        RBucket<RedisToken> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        tokenRBucket.delete();
    }

    public RedisToken tokenInfo(String tokenString){
        RBucket<RedisToken> tokenRBucket = securityTokenRedisClient.getBucket(tokenString);
        RedisToken token = tokenRBucket.get();
        return token;
    }

    public void loginOut(String tokenString){
        Subject subject = SecurityUtils.getSubject();
        removeToken(tokenString);
        subject.logout();
    }
}
