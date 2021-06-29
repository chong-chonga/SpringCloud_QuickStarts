package com.huanglexin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LeXin Huang
 * @date 2021年04月21日 16:35
 */
@EnableTransactionManagement
@EnableEurekaClient
@SpringBootApplication
public class ServicePaymentProviderApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(ServicePaymentProviderApplication8002.class, args);
    }
}
