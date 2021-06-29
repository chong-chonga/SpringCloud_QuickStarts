package com.huanglexin.springcloud.controlleradvice;

import com.huanglexin.springcloud.exception.ApiException;
import com.huanglexin.springcloud.exception.param.RequestDataException;
import com.huanglexin.springcloud.result.ApiResult;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author LeXin Huang
 * @date 2021年05月12日 15:56
 */
@Order(0)
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 出现 validation等注解相关错误时
     * @param e 异常
     * @return API状态信息
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {BindException.class})
    public ApiResult<Object> handleRequestDataException(BindException e) {
        return ApiResult.error(-400, e.getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
            MissingRequestValueException.class})
    public ApiResult<Object> handleRequestDataException() {
        return ApiResult.error(-400, "请求数据发生错误");
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {ApiException.class})
    public ApiResult<Object> handleRequestDataException(ApiException e) {
        return ApiResult.error(e.getCode(), e.getMessage());
    }
}
