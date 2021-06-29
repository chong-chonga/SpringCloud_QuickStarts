package com.huanglexin.springcloud.vo;

import com.huanglexin.springcloud.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author LeXin Huang
 * @date 2021年06月25日 14:03
 */
@Data
@ToString
public class UserVO implements Serializable {
    UserDTO user;

    public UserVO(UserDTO user) {
        this.user = user;
    }
}
