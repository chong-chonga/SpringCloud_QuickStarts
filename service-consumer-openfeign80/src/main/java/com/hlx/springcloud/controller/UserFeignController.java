package com.hlx.springcloud.controller;

import com.hlx.springcloud.client.UserClient;
import com.huanglexin.springcloud.result.ApiResult;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LeXin Huang
 * @date 2021年06月28日 16:59
 */
@RestController
public class UserFeignController {
    @Resource
    UserClient userClient;

    @GetMapping("/users/{userId}")
    public ApiResult<?> getUserInfo(@PathVariable("userId") String userId) {
        return userClient.getUserInfo(userId);
    }
}
