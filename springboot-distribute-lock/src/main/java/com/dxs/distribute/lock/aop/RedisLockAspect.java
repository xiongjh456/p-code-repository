package com.dxs.distribute.lock.aop;

import com.dxs.distribute.lock.aop.annotation.RedisLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Aspect
@Component
public class RedisLockAspect {
    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Pointcut("execution(* com.gov.controller..*.*(..)) || execution(* com.gov.enterprise.controller..*.*(..))")
    public void power() {

    }

    @Around("power() && @annotation(com.gov.enterprise.aop.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws InterruptedException {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.value();
        if (StringUtils.isEmpty(key)) {
            Object[] args = proceedingJoinPoint.getArgs();
            key = Arrays.toString(args);
        }

//        boolean result = redisDistributedLock.lock(key, redisLock.expireMills(), redisLock.retryTimes(), redisLock.retryDurationMills());
//        if (!result) {
//            log.info("--------------------------redis lock failed, key: [{}]", key);
//            return null;
//        }
//
//        log.info("------------------------redis lock success, key: [{}]", key);
//        try{
//            proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            log.error("executed occurred an exception", throwable);
//        } finally {
//            boolean successFlag = redisDistributedLock.unlock(key);
//            log.info("---------------redis release lock, key: [{}], successFlag: [{}]", key, successFlag);
//        }
//        return null;

        Lock lock = redisLockRegistry.obtain(key);
        try {
            boolean lockFlag = lock.tryLock(1, TimeUnit.MILLISECONDS);
            if (lockFlag) {
                return proceedingJoinPoint.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
//            TimeUnit.SECONDS.sleep(1);
            // 释放分布式锁
            lock.unlock();
        }
        return null;
    }
}
