package com.chenxii.jinghong.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.chenxii.jinghong.common", "com.chenxii.jinghong.goods"})
//@ComponentScan(basePackages = {"com.chenxii.jinghong.common", "com.chenxii.jinghong.goods"})
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
