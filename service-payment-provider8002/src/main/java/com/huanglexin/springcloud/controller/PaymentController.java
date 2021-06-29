package com.huanglexin.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanglexin.springcloud.entity.Payment;
import com.huanglexin.springcloud.result.ApiResult;
import com.huanglexin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.huanglexin.springcloud.param.InsertGroup;
import com.huanglexin.springcloud.param.PaymentParam;

import javax.annotation.Resource;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author LeXin Huang
 * @date 2021年06月23日 16:50
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @GetMapping("/{userId}/payments")
    public ApiResult<List<Payment>> getPaymentsHistory(@PathVariable("userId") Long userId,
                                                       @RequestParam("page") Long current) {
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Page<Payment> page = new Page<>();
        page.setCurrent(current);
        List<Payment> payments = paymentService.page(page, queryWrapper).getRecords();
        return ApiResult.ok(payments);
    }

    @PostMapping("/payments")
    public ApiResult<Object> createPayment(@Validated(value = InsertGroup.class)
                                           @RequestBody PaymentParam paymentParam) {
        var payment = paymentService.save(paymentParam);
        return ApiResult.ok(payment);
    }

    @GetMapping("/payments/{paymentId}")
    public ApiResult<Payment> getPaymentInfo(@PathVariable(value = "paymentId")
                                             @Positive(message = "请求数据发生错误") Long paymentId) {
        var payment = paymentService.getById(paymentId);
        return ApiResult.ok(payment);
    }

    @GetMapping("/payments")
    public ApiResult<List<Payment>> getPaymentsPage(@RequestParam("pn") Long pageNumber,
                                                    @RequestParam("ps") Long pageSize) {

        return ApiResult.ok();
    }
}
