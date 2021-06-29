package com.huanglexin.springcloud.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LeXin Huang
 * @date 2021年05月06日 21:53
 */
@TableName("payment")
@Data
@ToString
public class Payment implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    Long id;

    @TableField(value = "payment_money")
    Long money;

    @TableField(value = "payment_create_time", fill = FieldFill.INSERT)
    Date createTime;

    @TableField(value = "payment_modify_time", fill = FieldFill.INSERT_UPDATE)
    Date modifyTime;

    @TableField(value = "user_id")
    Long userId;
}
