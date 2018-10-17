package com.zzy.mywebsitebackend.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class ExceptionAop {

    @Pointcut(value = "@annotation(com.zzy.mywebsitebackend.AOP.AnnotationTarget.ExceptionAopTarget) && args(request,e)")
    public void ExceptionAopPointCut(Exception e, HttpServletRequest request){ }

    @Around("ExceptionAopPointCut(e,request)")
    @ResponseBody
    public ResponseEntity ExceptionAround(ProceedingJoinPoint joinPoint,Exception e,HttpServletRequest request) throws Throwable {
        ResponseEntity responseEntity = (ResponseEntity) joinPoint.proceed();
        log.info("IP:"+request.getRemoteAddr()+"  URI:"+request.getRequestURI()+" method:"+request.getMethod());
        log.error(responseEntity.getBody().toString());
        return responseEntity;
    }


}
