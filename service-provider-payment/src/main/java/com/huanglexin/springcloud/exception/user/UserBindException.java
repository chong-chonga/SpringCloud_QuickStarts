package com.huanglexin.springcloud.exception.user;

import com.huanglexin.springcloud.exception.ApiException;

/**
 * @author LeXin Huang
 * @date 2021年05月09日 17:03
 */
public class UserBindException extends ApiException {
    public UserBindException(String msg) {
        super(300001, msg);
    }
}
