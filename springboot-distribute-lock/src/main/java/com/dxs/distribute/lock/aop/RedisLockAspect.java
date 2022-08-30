package com.dxs.distribute.lock.aop;

import com.dxs.distribute.lock.constants.SystemErrorMsgConstant;
import com.dxs.distribute.lock.utils.SysResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Aspect
@Component
public class RedisLockAspect {
    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Pointcut("execution(* com.dxs.distribute..*.*(..)) || execution(* com.dxs.distribute.lock.controller.*.*(..))")
    public void power() { }

    @Around("power() && @annotation(com.dxs.distribute.lock.aop.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws InterruptedException {
        Object[] args = proceedingJoinPoint.getArgs();
        String key = Arrays.toString(args);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(servletRequestAttributes)) {
            return SysResult.fail(SystemErrorMsgConstant.FAILURE_ERROR);
        }
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.hasLength(token)) {
            key += token;
        }

        Lock lock = redisLockRegistry.obtain(key);
        try {
            boolean lockFlag = lock.tryLock(1, TimeUnit.MILLISECONDS);
            if (lockFlag) {
                return proceedingJoinPoint.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(200);
            // 释放分布式锁
            lock.unlock();
        }
        return SysResult.fail();
    }
}
