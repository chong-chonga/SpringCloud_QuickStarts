package com.huanglexin.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huanglexin.springcloud.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LeXin Huang
 * @date 2021年05月06日 16:08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByEmailAddress(@Param("userEmailAddress") String userEmailAddress);

    User selectLoginUser(@Param("username") String userName, @Param("password") String password);
}
