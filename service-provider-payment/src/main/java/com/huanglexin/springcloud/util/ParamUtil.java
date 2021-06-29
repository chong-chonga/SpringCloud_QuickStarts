package com.huanglexin.springcloud.util;

import com.huanglexin.springcloud.exception.param.RequestDataException;

/**
 * @author LeXin Huang
 * @date 2021年05月12日 15:20
 */
@Deprecated
public class ParamUtil {

    private ParamUtil() {}

    public static Long convertToLong(String param) {
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            throw new RequestDataException("请求数据发生错误!");
        }
    }
}
