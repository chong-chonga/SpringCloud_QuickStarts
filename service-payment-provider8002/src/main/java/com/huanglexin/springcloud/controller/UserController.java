package com.huanglexin.springcloud.controller;

import cn.hutool.crypto.SecureUtil;
import com.huanglexin.springcloud.dto.UserDTO;
import com.huanglexin.springcloud.param.InsertGroup;
import com.huanglexin.springcloud.param.UserParam;
import com.huanglexin.springcloud.result.ApiResult;
import com.huanglexin.springcloud.service.UserService;
import com.huanglexin.springcloud.util.AuthorizationUtil;
import com.huanglexin.springcloud.util.RedisUtil;
import com.huanglexin.springcloud.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Positive;

/**
 * @author LeXin Huang
 * @date 2021年04月21日 16:38
 */
@Api(tags = "用户模块")
@Slf4j
@RestController
public class UserController {

    @Resource
    UserService userService;

    @Value("${server.port}")
    String port;

    private static final String USER_ID_SET_KEY = "userIdSet";

    @ApiOperation(value = "获取指定id用户信息", consumes = "application/json", produces = "application/json")
    @GetMapping("/users/{userId}")
    public ApiResult<UserDTO> getUserInfo(@PathVariable(value = "userId") @Positive Long userId) {
        UserDTO userDTO = null;
        boolean exists = RedisUtil.exists(USER_ID_SET_KEY, userId);
        if (exists) {
            userDTO = userService.getUserInfoById(userId);
        }

        return ApiResult.ok(userDTO);
    }

    @ApiOperation(value = "注册用户", consumes = "application/json", produces = "application/json")
    @PostMapping("/users")
    public ApiResult<Object> register(@Validated(value = InsertGroup.class)
                                      @RequestBody UserParam registerParam) {
        String password = registerParam.getPassword();
        registerParam.setPassword(SecureUtil.md5(password));
        long userId = userService.save(registerParam);
        RedisUtil.opsSet(USER_ID_SET_KEY, userId);
        return ApiResult.ok();
    }

    @ApiOperation(value = "登录", consumes = "application/json", produces = "application/json")
    @PostMapping("/login")
    public ApiResult<LoginVO> login(@Validated(value = {UserParam.LoginGroup.class})
                                    @RequestBody UserParam loginParam) {
        String password = loginParam.getPassword();
        loginParam.setPassword(SecureUtil.md5(password));
        var user = userService.login(loginParam.getUsername(), loginParam.getPassword());
        String token = AuthorizationUtil.authorize(user);

        return ApiResult.ok(new LoginVO(token, new UserDTO(user)));
    }

    @ApiOperation(value = "认证", consumes = "application/json", produces = "application/json")
    @GetMapping("/auth")
    public ApiResult<LoginVO> login(@RequestHeader(value = "token") String token) {
        var user = AuthorizationUtil.authentication(token);
        return ApiResult.ok(new LoginVO(token, new UserDTO(user)));
    }

    @GetMapping("/loadBalancing")
    public ApiResult<String> balance() {
        return ApiResult.ok(port);
    }
}
