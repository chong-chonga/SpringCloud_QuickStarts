package com.huanglexin.springcloud.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LeXin Huang
 * @date 2021年05月06日 16:09
 */
@TableName("user")
@Data
@ToString
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_email_address")
    private String emailAddress;

    @TableField(value = "user_username")
    private String username;

    @TableField(value = "user_password")
    private String password;

    @TableField(value = "user_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "user_modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

}
