package com.zzy.mywebsitebackend.AOP.Entity;

import java.util.Date;
import java.util.Map;

public class ExceptionInfo {
    Long id;
    String ip;
    String url;
    String method;
    String requestBody;
    Integer httpCode;
    Date time = new Date();
    Map<String,String> headers;
    String exceptionName;
    String exceptionMessage;
    String exceptionStackTrace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionStackTrace() {
        return exceptionStackTrace;
    }

    public void setExceptionStackTrace(String exceptionStackTrace) {
        this.exceptionStackTrace = exceptionStackTrace;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ExceptionInfo{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", httpCode=" + httpCode +
                ", headers=" + headers +
                ", exceptionName='" + exceptionName + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", exceptionStackTrace='" + exceptionStackTrace + '\'' +
                ", time=" + time +
                '}';
    }
}
