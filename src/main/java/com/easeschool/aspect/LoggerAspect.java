package com.easeschool.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.easeschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toString() + "Method execution started");
        Instant start = Instant.now();
       Object obj = joinPoint.proceed();
        Instant end = Instant.now();
        long time =Duration.between(start, end).toMillis();
        log.info("time taken by method: " + time);
 return obj;
    }

    @AfterThrowing(value = "execution(* com.easeschool.*.*.*(..))" , throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint,Exception ex) throws Throwable {
        log.error(joinPoint.getSignature().toString() + "exception occur" + ex.getMessage());
    }


}
