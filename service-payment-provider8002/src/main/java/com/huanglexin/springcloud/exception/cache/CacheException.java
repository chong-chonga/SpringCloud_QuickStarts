package com.huanglexin.springcloud.exception.cache;

import com.huanglexin.springcloud.exception.ApiException;

/**
 * @author LeXin Huang
 * @date 2021年06月25日 10:12
 */
public class CacheException extends ApiException {
    public CacheException(String msg) {
        super(2001, msg);
    }
}
