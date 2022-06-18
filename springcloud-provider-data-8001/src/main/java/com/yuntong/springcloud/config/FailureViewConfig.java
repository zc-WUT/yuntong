package com.yuntong.springcloud.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FailureViewConfig {

    @ExceptionHandler(RuntimeException.class)
    public String handException(RuntimeException e){
        if (e instanceof AccessDeniedException){
            return "failure/403";
        }
        return "failure/404";
    }
}
