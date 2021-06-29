package com.huanglexin.springcloud.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Lexin Huang
 * @since 2.0
 */
@Data
@ToString
public class ApiResult<T> implements Serializable {

    /**
     * Status-code 状态码
     */
    private Integer code;

    /**
     * 请求状态信息
     */
    private String msg;

    private String message;

    /**
     * Data 数据实体
     */
    private Object data;

    public ApiResult() {

    }

    private ApiResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = this.message = msg;
        this.data = data;
    }

    @Data
    @ToString
    private static class EmptyObject implements Serializable{
        Byte a;

    }

    public static <T> ApiResult<T> error(Integer code, String message) {
        return new ApiResult<>(code, message, null);
    }

    public static <T> ApiResult<T> ok() {
        return new ApiResult<>(0, "", new EmptyObject());
    }

    public static <T> ApiResult<T> ok(T data) {
        if (data == null) {
            return ok();
        }
        return new ApiResult<>(0, "", data);
    }
}
