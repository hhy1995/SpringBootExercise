package com.springboot.demo.error;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException{

    public BusinessException(){}
    public BusinessException(String message) {
        super(message);
    }
}
