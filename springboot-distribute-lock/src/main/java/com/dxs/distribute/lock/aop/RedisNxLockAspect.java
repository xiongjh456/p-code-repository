package com.dxs.distribute.lock.aop;

import com.dxs.distribute.lock.constants.SystemErrorMsgConstant;
import com.dxs.distribute.lock.utils.SysResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class RedisNxLockAspect {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Pointcut("execution(* com.dxs.distribute..*.*(..)) || execution(* com.dxs.distribute.lock.controller.*.*(..))")
    public void power() { }

    @Around("power() && @annotation(com.dxs.distribute.lock.aop.annotation.RedisNxLock)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
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

        boolean result = redisTemplate.opsForValue().setIfAbsent(key, "lock", 1, TimeUnit.SECONDS);
        if (result) {
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return SysResult.fail();
    }
}
