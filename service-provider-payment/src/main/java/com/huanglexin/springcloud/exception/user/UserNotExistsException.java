package com.huanglexin.springcloud.exception.user;

import com.huanglexin.springcloud.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用于表示查询不到指定用户信息时的异常
 * @author LeXin Huang
 * @date 2021年05月06日 22:12
 */
@ResponseStatus(HttpStatus.OK)
public class UserNotExistsException extends ApiException {
    public UserNotExistsException(String msg) {
        super(3002, msg);
    }
}
