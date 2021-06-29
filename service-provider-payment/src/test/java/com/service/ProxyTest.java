package com.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.lang.reflect.Proxy;

/**
 * @author LeXin Huang
 * @date 2021年05月17日 16:20
 */
@Slf4j
public class ProxyTest {

    private interface ExceptionHandler {
        void handle(Exception e);
    }

    static class ExceptionHandlerImpl implements ExceptionHandler {

        @Override
        public void handle(Exception e) {
            log.info("--------handle方法执行--------");
        }
    }

    @DisplayName(value = "测试动态代理")
    public void testProxy1() {
        ExceptionHandler handler = new ExceptionHandlerImpl();

        ExceptionHandler proxyObject = (ExceptionHandler) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                new Class[]{ExceptionHandler.class}, (proxy, method, args) -> {
                    Assert.isTrue(args[0] instanceof Exception, "给定的参数非法!");
                    Exception e = (Exception) args[0];

                    log.info("异常的信息是" + e.getMessage());
                    return method.invoke(handler, args[0]);
                });
        try {
            throw new RuntimeException("runtimeException");
        } catch (Exception e) {
            proxyObject.handle(e);
        }
    }

    @SuppressWarnings("resources")
    @Test
    public void test2() {
    }
}
