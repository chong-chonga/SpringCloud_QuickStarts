package com.hlx.springcloud.client;

import com.huanglexin.springcloud.result.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LeXin Huang
 * @date 2021年06月28日 17:01
 */
@FeignClient(value = "SERVICE-PROVIDER-PAYMENT")
public interface UserClient {
    @GetMapping("/users/{userId}")
    ApiResult<?> getUserInfo(@PathVariable("userId") String userId);
}
