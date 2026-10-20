package com.kuheindra.employees.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around(
            "execution(* com.kuheindra.employees.controller..*(..))")
    public Object logController(
            ProceedingJoinPoint joinPoint)
            throws Throwable {

        String method =
                joinPoint.getSignature().toShortString();

        log.info(
                "Incoming Request => {} Args={}",
                method,
                Arrays.toString(joinPoint.getArgs()));

        long start = System.currentTimeMillis();

        Object result =
                joinPoint.proceed();

        long end =
                System.currentTimeMillis();

        log.info(
                "Outgoing Response => {} ExecutionTime={}ms Response={}",
                method,
                end - start,
                result);

        return result;
    }
}