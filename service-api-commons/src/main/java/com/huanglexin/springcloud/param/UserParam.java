package com.huanglexin.springcloud.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author LeXin Huang
 * @date 2021年05月07日 11:18
 */
@Data
@ToString
@ApiModel(value= "设置用户信息")
public class UserParam {

    @NotNull(message = "请求数据发生错误", groups = {InsertGroup.class})
    @Email(message = "请填写正确的邮箱!", groups = {InsertGroup.class})
    @ApiModelProperty(value = "邮箱")
    private String emailAddress;

    @NotNull(message = "请求数据发生错误", groups = {InsertGroup.class})
    @Length(min = 1, max = 18, message = "用户名必须在 1 -  18 个字符以内!",groups = {InsertGroup.class, LoginGroup.class})
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotNull(message = "请求数据发生错误", groups = {InsertGroup.class})
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$",
            message = "密码至少包含数字和英文字母，长度6-20", groups = {InsertGroup.class, LoginGroup.class})
    @ApiModelProperty(value = "密码")
    private String password;

    public interface LoginGroup{

    }

}
