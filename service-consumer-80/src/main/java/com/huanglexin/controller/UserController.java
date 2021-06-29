package com.huanglexin.controller;

import com.huanglexin.springcloud.result.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.huanglexin.springcloud.param.InsertGroup;
import com.huanglexin.springcloud.param.UserParam;

import javax.annotation.Resource;

/**
 * @author LeXin Huang
 * @date 2021年05月09日 16:24
 */
@RestController
public class UserController {

    @Resource
    RestTemplate restTemplate;

    public static final String SERVICE_URL_PREFIX = "http://service-provider-payment";


    @PostMapping("/users")
    public ApiResult<?> register(@Validated(value = InsertGroup.class)
                                 @RequestBody UserParam registerParam) {

        return restTemplate.postForObject(SERVICE_URL_PREFIX + "/users", registerParam, ApiResult.class);
    }

    @PostMapping("/login")
    public ApiResult<?> login(@Validated(value = {UserParam.LoginGroup.class})
                                  @RequestBody UserParam loginParam) {
        return restTemplate.postForObject(SERVICE_URL_PREFIX + "/login", loginParam, ApiResult.class);
    }

    @GetMapping("/users/{userId}")
    public ApiResult<?> getUserInfo(@PathVariable("userId") String userId) {
        return restTemplate.getForObject(SERVICE_URL_PREFIX + "/users/" + userId, ApiResult.class);
    }

    @GetMapping("/loadBalancing")
    public ApiResult<?> testLoadBalance() {
        return restTemplate.getForObject(SERVICE_URL_PREFIX + "/loadBalancing", ApiResult.class);
    }

    @GetMapping("/users/entity/{id}")
    public ApiResult<?> getUserEntity() {
        ResponseEntity<ApiResult> forEntity = restTemplate.getForEntity(SERVICE_URL_PREFIX + "/loadBalancing", ApiResult.class);
        System.out.println(forEntity.getStatusCode());
        return forEntity.getBody();
    }
}
