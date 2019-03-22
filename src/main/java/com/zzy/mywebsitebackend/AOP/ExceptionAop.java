package com.zzy.mywebsitebackend.AOP;

import com.zzy.mywebsitebackend.AOP.Entity.ExceptionInfo;
import com.zzy.mywebsitebackend.Service.ExceptionInfoService;
import com.zzy.mywebsitebackend.Util.Util;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class ExceptionAop {

    @Autowired
    private ExceptionInfoService exceptionInfoService;

    @Pointcut(value = "@annotation(com.zzy.mywebsitebackend.AOP.AnnotationTarget.ExceptionAopTarget) && args(request,e)")
    public void ExceptionAopPointCut(Exception e, HttpServletRequest request){ }

    @Around("ExceptionAopPointCut(e,request)")
    @ResponseBody
    public ResponseEntity ExceptionAround(ProceedingJoinPoint joinPoint,Exception e,HttpServletRequest request) throws Throwable {
        ResponseEntity responseEntity = (ResponseEntity) joinPoint.proceed();
        e.printStackTrace();
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setId(new Date().getTime());
        exceptionInfo.setIp(Util.getIpAddress(request));
        exceptionInfo.setUrl(request.getRequestURI());
        exceptionInfo.setMethod(request.getMethod());
        exceptionInfo.setRequestBody(getRequestBodyWithUtf8(request));
        exceptionInfo.setHttpCode(responseEntity.getStatusCode().value());
        exceptionInfo.setHeaders(getRequestHeader(request));
        exceptionInfo.setExceptionName(e.getClass().getName());
        exceptionInfo.setExceptionMessage(e.getMessage());
        exceptionInfo.setExceptionStackTrace(getExceptionStackTrace(e));
        log.error(exceptionInfo.toString());
        exceptionInfoService.addExceptionInfo(exceptionInfo);
        return responseEntity;
    }

    private static String getRequestBodyWithUtf8(HttpServletRequest request){
        String ret = "";
        try(BufferedReader br = request.getReader()){
            String str = "";
            while((str = br.readLine()) != null){
                ret += str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return ret;
        }
    }

    private static Map<String,String> getRequestHeader(HttpServletRequest request){
        Map<String,String> headers = new IdentityHashMap<>();
        Enumeration<String> e = request.getHeaderNames();
        while(e.hasMoreElements()){
            String headerName = e.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            while(headerValues.hasMoreElements()){
                headers.put(headerName,headerValues.nextElement());
            }
        }
        return headers;
    }

    private static String getExceptionStackTrace(Exception e){
        String stackTrace = "";
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            stackTrace += stackTraceElement.getClassName()+"."+stackTraceElement.getMethodName()
                    +"("+stackTraceElement.getFileName()+":"+stackTraceElement.getLineNumber()+")\n";
        }
        return  stackTrace;
    }
}
