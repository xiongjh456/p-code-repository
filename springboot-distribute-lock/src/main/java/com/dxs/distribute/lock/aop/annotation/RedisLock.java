package com.dxs.distribute.lock.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {
    /**
     * lock key
     * */
    String value() default "";
    /**
     * expire time
     * */
    long expireMills() default 30000;
    /**
     * retry times
     * */
    int retryTimes() default 0;
    /**
     * retry duration time
     * */
    long retryDurationMills() default 200;

}
