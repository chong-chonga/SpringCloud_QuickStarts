package com.huanglexin.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanglexin.springcloud.entity.Payment;
import com.huanglexin.springcloud.param.PaymentParam;

/**
 * @author LeXin Huang
 * @date 2021年06月23日 16:52
 */
public interface PaymentService extends IService<Payment> {

    Payment save(PaymentParam paymentParam);
}
