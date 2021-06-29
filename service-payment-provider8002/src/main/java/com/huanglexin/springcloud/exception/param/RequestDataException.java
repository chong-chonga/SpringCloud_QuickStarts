package com.huanglexin.springcloud.exception.param;

import com.huanglexin.springcloud.exception.ApiException;

/**
 * @author LeXin Huang
 * @date 2021年05月12日 15:55
 */
@Deprecated
public class RequestDataException extends ApiException {
    public RequestDataException(String msg) {
        super(-400, msg);
    }
}
