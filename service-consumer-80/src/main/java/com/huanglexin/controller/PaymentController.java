package com.huanglexin.controller;

import com.huanglexin.springcloud.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.huanglexin.springcloud.param.InsertGroup;
import com.huanglexin.springcloud.param.PaymentParam;

import javax.annotation.Resource;

/**
 * @author LeXin Huang
 * @date 2021年06月23日 16:50
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/{userId}/payments")
    public ApiResult<?> getPaymentsHistory(@PathVariable("userId") Long userId,
                                           @RequestParam("page") Long current) {
        return ApiResult.ok();
    }

    @PostMapping("/payments")
    public ApiResult<?> createPayment(@Validated(value = InsertGroup.class)
                                      @RequestBody PaymentParam paymentParam) {
        return restTemplate.postForObject(UserController.SERVICE_URL_PREFIX + "/payments", paymentParam, ApiResult.class);
    }

    @GetMapping("/payments/{paymentId}")
    public ApiResult<?> getPaymentInfo(@PathVariable(value = "paymentId") Long paymentId) {
        return restTemplate.getForObject(UserController.SERVICE_URL_PREFIX + "/payments/" + paymentId, ApiResult.class);
    }

    @GetMapping("/payments")
    public ApiResult<?> getPaymentsPage(@RequestParam("pn") Long pageNumber,
                                        @RequestParam("ps") Long pageSize) {
        return restTemplate.getForObject(UserController.SERVICE_URL_PREFIX + "/payments?pn=" + pageNumber + "&ps=" + pageSize, ApiResult.class);
    }
}
