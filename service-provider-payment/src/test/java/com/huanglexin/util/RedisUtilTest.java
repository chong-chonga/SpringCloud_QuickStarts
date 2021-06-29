package com.huanglexin.util;

import com.huanglexin.springcloud.ServicePaymentProviderApplication8001;
import com.huanglexin.springcloud.util.SpringContextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LeXin Huang
 * @date 2021年06月26日 10:04
 */
@SpringBootTest(classes = ServicePaymentProviderApplication8001.class)
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
