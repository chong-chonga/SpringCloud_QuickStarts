package com.huanglexin.springcloud.util;

import com.huanglexin.springcloud.exception.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * @author LeXin Huang
 * @date 2021年05月10日 17:16
 */
public class RedisUtil {
    private static RedisTemplate<Object,Object> redisTemplate = SpringContextUtil.getBean("redisTemplate",
            RedisTemplate.class);

    public static Object get(Object k) {
        return redisTemplate.opsForValue().get(k);
    }

    public static boolean exists(Object setKey, Object val) {
        Boolean member = redisTemplate.opsForSet().isMember(setKey, val);
        if (Objects.isNull(member)) {
            throw new CacheException("key 为" + setKey + "的set集合在进行isMember()判断时返回了NULL!");
        }
        return member;
    }

    public static void opsSet(Object setKey, Object val) {
        redisTemplate.opsForSet().add(setKey, val);
    }
}
