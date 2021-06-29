package com.huanglexin.springcloud.exception.table;

import com.huanglexin.springcloud.exception.ApiException;

/**
 * @author LeXin Huang
 * @date 2021年05月06日 22:16
 */
public class InsertException extends ApiException {
    public InsertException(String msg) {
        super(500001, msg);
    }
}
