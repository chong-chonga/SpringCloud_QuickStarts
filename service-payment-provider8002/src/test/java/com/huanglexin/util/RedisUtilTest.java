package com.huanglexin.util;

import com.huanglexin.springcloud.ServicePaymentProviderApplication8002;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author LeXin Huang
 * @date 2021年06月26日 10:04
 */
@SpringBootTest(classes = ServicePaymentProviderApplication8002.class)
public class RedisUtilTest {
    @Resource
    RedisTemplate<Object,Object> redisTemplate;
    
    @Test
    public void test() {
        try {
            Set<Object> userIdKey = redisTemplate.opsForSet().members(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
