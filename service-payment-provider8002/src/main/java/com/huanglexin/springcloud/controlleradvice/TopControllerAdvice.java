package com.huanglexin.springcloud.controlleradvice;

import com.huanglexin.springcloud.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LeXin Huang
 * @date 2021年06月25日 10:17
 */
@RestControllerAdvice
@Slf4j
@Order(1)
public class TopControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    public ApiResult<Object> handleAll(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return ApiResult.error(500, "服务器异常");
    }
}
