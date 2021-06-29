package com.huanglexin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LeXin Huang
 * @date 2021年04月21日 16:35
 */
@EnableTransactionManagement
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ServicePaymentProviderApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(ServicePaymentProviderApplication8001.class, args);
    }
}
