package com.huanglexin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LeXin Huang
 * @date 2021年05月09日 9:37
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceConsumerApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication80.class, args);
    }
}
