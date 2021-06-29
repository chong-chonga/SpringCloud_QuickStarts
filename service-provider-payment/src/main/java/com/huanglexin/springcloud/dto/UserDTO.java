package com.huanglexin.springcloud.dto;

import com.huanglexin.springcloud.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author LeXin Huang
 * @date 2021年04月21日 16:40
 */
@Data
@ToString
public class UserDTO implements Serializable {
    Long uid;

    String username;

    String userEmailAddress;

    public UserDTO(User user) {
        this.uid = user.getId();
        this.username = user.getUsername();
        this.userEmailAddress = user.getEmailAddress();
    }
}
