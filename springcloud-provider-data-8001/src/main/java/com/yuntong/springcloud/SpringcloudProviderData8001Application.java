package com.yuntong.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient //自动在服务启动后自动注册到eureka中
@EnableDiscoveryClient //服务发现
@MapperScan("com.yuntong.springcloud.dao")
@EnableAsync //开去异步功能注解
@EnableScheduling//开启注解的定时功能注解
public class SpringcloudProviderData8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudProviderData8001Application.class, args);
    }

}
