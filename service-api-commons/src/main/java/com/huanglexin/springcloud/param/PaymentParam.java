package com.huanglexin.springcloud.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author LeXin Huang
 * @date 2021年06月23日 17:41
 */
@Data
@ToString
@ApiModel(value= "账单信息")
public class PaymentParam {
    @NotNull(message = "请求数据发生错误", groups = {InsertGroup.class})
    @Positive(message = "金额必须大于0", groups = {InsertGroup.class})
    Long count;

    @NotNull(message = "请求数据发生错误", groups = {InsertGroup.class})
    @Positive(message = "请输入正确的用户id", groups = {InsertGroup.class})
    Long userId;

}
