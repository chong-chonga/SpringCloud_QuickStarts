package com.huanglexin.springcloud.service;

import com.huanglexin.springcloud.dto.UserDTO;
import com.huanglexin.springcloud.exception.user.UserNotExistsException;
import com.huanglexin.springcloud.param.UserParam;
import com.huanglexin.springcloud.entity.User;

/**
 * @author LeXin Huang
 * @date 2021年05月06日 22:07
 */
public interface UserService {
    /**
     * 根据id获取用户信息
     * @param userId 用户id
     * @return 用户DTO
     * @throws UserNotExistsException 当指定id的用户不存在时
     */
    UserDTO getUserInfoById(Long userId) throws UserNotExistsException;

    /**
     * 新增用户
     * @param userParam 用户信息
     * @return 用户id
     */
    long save(UserParam userParam);

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return 用户对象
     * @throws UserNotExistsException 当给定用户密码组合有误时
     */
    User login(String userName, String password) throws UserNotExistsException;
}
