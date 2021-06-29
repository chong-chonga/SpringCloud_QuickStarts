package com.huanglexin.springcloud.exception.auth;

import com.huanglexin.springcloud.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author LeXin Huang
 * @date 2021年06月25日 13:00
 */
@ResponseStatus(HttpStatus.OK)
public class InvalidTokenException extends ApiException {
    public InvalidTokenException(String msg) {
        super(1001, msg);
    }
}
