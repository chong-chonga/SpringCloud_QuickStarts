package com.huanglexin.springcloud.service.user;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.huanglexin.springcloud.dto.UserDTO;
import com.huanglexin.springcloud.exception.table.InsertException;
import com.huanglexin.springcloud.exception.user.UserBindException;
import com.huanglexin.springcloud.exception.user.UserNotExistsException;
import com.huanglexin.springcloud.mapper.UserMapper;
import com.huanglexin.springcloud.param.UserParam;
import com.huanglexin.springcloud.entity.User;
import com.huanglexin.springcloud.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author LeXin Huang
 * @date 2021年05月06日 22:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDTO getUserInfoById(Long userId) {
        var user = userMapper.selectById(userId);
        if (null == user) {
            throw new UserNotExistsException("id 为 " + userId + "的用户不存在!");
        }
        return new UserDTO(user);
    }

    @Transactional(rollbackFor = InsertException.class)
    @Override
    public long save(UserParam userParam) {
        var queryRes = userMapper.selectByEmailAddress(userParam.getEmailAddress());
        if (null != queryRes) {
            throw new UserBindException("该邮箱已被注册!");
        }
        var user = new User();
        user.setEmailAddress(userParam.getEmailAddress());
        user.setUsername(userParam.getUsername());
        user.setPassword(userParam.getPassword());
        if(!SqlHelper.retBool(userMapper.insert(user))) {
            throw new InsertException("新增用户失败!");
        }
        if (null == user.getId()) {
            throw new InsertException("插入操作没有正确返回用户id");
        }
        return user.getId();
    }

    @Override
    public User login(String userName, String password) {
        var user = userMapper.selectLoginUser(userName, password);
        if (null == user) {
            throw new UserNotExistsException("用户名或密码有误");
        }
        return user;
    }

}
