package com.huanglexin.springcloud.exception;

import lombok.Getter;

/**
 * @author LeXin Huang
 * @date 2021年05月06日 22:12
 */
@Getter
public abstract class ApiException extends RuntimeException{
    private final Integer code;

    private final String msg;

    private final String message;

    protected ApiException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.message = this.msg =  msg;
    }

}
