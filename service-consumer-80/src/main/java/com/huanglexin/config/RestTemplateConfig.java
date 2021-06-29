package com.huanglexin.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author LeXin Huang
 * @date 2021年05月12日 16:02
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 开启具有负载均衡功能的 RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
