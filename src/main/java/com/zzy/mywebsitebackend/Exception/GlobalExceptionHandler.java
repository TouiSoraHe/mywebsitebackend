package com.zzy.mywebsitebackend.Exception;

import com.zzy.mywebsitebackend.AOP.AnnotationTarget.ExceptionAopTarget;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ExceptionAopTarget
    public ResponseEntity MethodArgumentErrorHandler(HttpServletRequest request,MethodArgumentNotValidException e){
        e.printStackTrace();
        return  new ResponseEntity(e.getClass().getName()+":"+e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ExceptionAopTarget
    public ResponseEntity DefaultErrorHandler(HttpServletRequest request,Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getClass().getName()+":"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
