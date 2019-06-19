package com.wud.serviceribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.wud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient

@EnableHystrix
//加载自定义Ribbon配置类
@RibbonClient(name = "SERVICE-HI",configuration = MySelfRule.class)
public class ServiceRibbonApplication {
    //负载均衡
    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

//    @Bean
//    public IRule myrule(){
//        return new RoundRobinRule();//定义算法负载均衡
//    }
}
