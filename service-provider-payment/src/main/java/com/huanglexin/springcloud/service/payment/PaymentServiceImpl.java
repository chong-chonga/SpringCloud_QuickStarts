package com.huanglexin.springcloud.service.payment;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanglexin.springcloud.entity.Payment;
import com.huanglexin.springcloud.exception.table.InsertException;
import com.huanglexin.springcloud.mapper.PaymentMapper;
import com.huanglexin.springcloud.param.PaymentParam;
import com.huanglexin.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author LeXin Huang
 * @date 2021年06月23日 16:52
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Payment save(PaymentParam paymentParam) {
        Payment payment = new Payment();
        payment.setUserId(paymentParam.getUserId());
        payment.setMoney(paymentParam.getCount());
        if (!save(payment)) {
            throw new InsertException("创建订单失败!");
        }
        Long id = payment.getId();
        Objects.requireNonNull(id, "插入操作后, 对象的主键未成功设置!");
        return payment;
    }
}
