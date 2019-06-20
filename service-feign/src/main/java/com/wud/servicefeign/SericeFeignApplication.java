package com.wud.servicefeign;

import com.wud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = {"com.wud.cloud.service"})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wud.cloud.service"})
@EnableHystrix

//@ComponentScan(basePackages = {"com.wud.cloud.service"})
//@RibbonClient(name = "SERVICE-HI",configuration = MySelfRule.class)//feign同时可以使用ribbon的负载均衡机制
public class SericeFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SericeFeignApplication.class, args);
    }

}
