package com.huanglexin.springcloud.vo;

import com.huanglexin.springcloud.dto.UserDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author LeXin Huang
 * @date 2021年06月25日 14:15
 */
@Data
@ToString
public class LoginVO implements Serializable {
    String token;

    UserDTO user;

    public LoginVO(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }
}
