package com.hlx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LeXin Huang
 * @date 2021年06月28日 16:41
 */
@SpringBootApplication
@EnableFeignClients
public class ServiceConsumerOpenFeignApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerOpenFeignApplication80.class, args);
    }
}
