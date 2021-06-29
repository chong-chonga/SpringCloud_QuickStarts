package com.huanglexin.springcloud.util;

import com.huanglexin.springcloud.entity.User;
import com.huanglexin.springcloud.exception.auth.InvalidTokenException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author LeXin Huang
 * @date 2021年05月12日 14:32
 */
public class AuthorizationUtil {

    private static final StringRedisTemplate STRING_REDIS_TEMPLATE = SpringContextUtil.getBean("stringRedisTemplate",
            StringRedisTemplate.class);

    private static final RedisTemplate<String, User> USER_REDIS_TEMPLATE = SpringContextUtil.getBean("userRedisTemplate",
            RedisTemplate.class);

    private AuthorizationUtil() {

    }

    public static String authorize(User user) {
        String token = STRING_REDIS_TEMPLATE.opsForValue().get(user.getEmailAddress());
        if (null == token) {
            token = UUID.randomUUID().toString();
        }
        STRING_REDIS_TEMPLATE.opsForValue().set(user.getEmailAddress(), token, 7L, TimeUnit.DAYS);
        USER_REDIS_TEMPLATE.opsForValue().set(token, user, 7L, TimeUnit.DAYS);
        return token;
    }

    public static User authentication(String token) {
        if (token == null) {
            throw new InvalidTokenException("登录已失效!");
        }
        var user = USER_REDIS_TEMPLATE.opsForValue().get(token);
        if (user == null) {
            throw new InvalidTokenException("登录已失效!");
        }
        STRING_REDIS_TEMPLATE.opsForValue().set(user.getEmailAddress(), token, 7L, TimeUnit.DAYS);
        USER_REDIS_TEMPLATE.opsForValue().set(token, user, 7L, TimeUnit.DAYS);
        return user;
    }
}
