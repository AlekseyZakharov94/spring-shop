package com.geekbrains.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LogAspect {

    private long start;
    private long finish;

    @Before("@annotation(log)")
    protected Object logBefore(Log log){
        start = System.currentTimeMillis();
        return null;
    }

    @Around("@annotation(log)")
    protected Object logAround(ProceedingJoinPoint p, Log log) throws Throwable {
        System.out.println("заказ создан");
        p.proceed();
        return p;
    }

    @After("@annotation(log)")
    protected Object logAfter(Log log){
        finish = System.currentTimeMillis();
        System.out.println("Время создания: " + (finish-start) + " ms");
        return null;
    }
}
