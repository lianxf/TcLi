package commons.boot.aop;

import cn.likepeng.commons.core.utils.ValidateUtil;
import commons.boot.annotation.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
public class DistributedLockAop {

    @Autowired(required = false)
    RedissonClient distributedLockClient;

    @Pointcut("@annotation(distributedLock)")
    public void lockPointCut(DistributedLock distributedLock){}

    @Around("lockPointCut(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        Signature sig = joinPoint.getSignature();

        String name = distributedLock.name();
        long timeOut = Long.parseLong(distributedLock.timeOut());
        TimeUnit timeUnit = distributedLock.timeUnit();

        if (ValidateUtil.isEmpty(name)){
            name = sig.getName();
        }

        RLock fairLock = distributedLockClient.getFairLock(name);
        fairLock.tryLock(timeOut,timeOut-timeOut/5,timeUnit);
        boolean locked = fairLock.isLocked();
        if (locked){
         log.info("方法:"+name+"\t已加分布式锁:"+name);
        }
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } finally {
            log.info("方法:"+name+"\t已解分布式锁:"+name);
            fairLock.unlock();
        }
    }
}
