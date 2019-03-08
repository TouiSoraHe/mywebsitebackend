package com.zzy.mywebsitebackend.Exception;

import com.zzy.mywebsitebackend.AOP.AnnotationTarget.ExceptionAopTarget;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕捉所有Shiro异常
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    @ExceptionAopTarget
    public ResponseEntity handle401(ShiroException e) {
        return new ResponseEntity("未认证(Unauthenticated):" + e.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    /**
     * 单独捕捉Shiro(UnauthenticatedException)异常
     * 该异常为以游客身份访问有权限管控的请求无法对匿名主体进行授权，而授权失败所抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ExceptionAopTarget
    public ResponseEntity handle401(UnauthenticatedException e) {
        return new ResponseEntity("未认证(Unauthenticated):" + e.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    /***
     * 认证成功,但是没有所需权限
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ExceptionAopTarget
    public ResponseEntity handle403(UnauthorizedException e) {
        return new ResponseEntity("无权访问(Unauthorized):" + e.getMessage(),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ExceptionAopTarget
    public ResponseEntity MethodArgumentErrorHandler(HttpServletRequest request,MethodArgumentNotValidException e){
        return  new ResponseEntity(this.getValidError(e.getBindingResult().getFieldErrors()).get("errorMsg"),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ExceptionAopTarget
    public ResponseEntity MethodArgumentTypeMismatchExceptionHandler(HttpServletRequest request,MethodArgumentTypeMismatchException e){
        return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ExceptionAopTarget
    public ResponseEntity DefaultErrorHandler(HttpServletRequest request,Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getClass().getName()+":"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 获取校验错误信息
     * @param fieldErrors
     * @return
     */
    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> result = new HashMap<String, Object>(16);
        List<String> errorList = new ArrayList<String>();
        StringBuffer errorMsg = new StringBuffer("校验异常(ValidException):");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField() + "-" + error.getDefaultMessage() + ".");
        }
        result.put("errorList", errorList);
        result.put("errorMsg", errorMsg);
        return result;
    }
}
