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
        return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ExceptionAopTarget
    public ResponseEntity DefaultErrorHandler(HttpServletRequest request,Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
