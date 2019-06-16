package com.wud.serviceribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced  //负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
//    @Bean
//    public IRule myrule(){
//        return new RoundRobinRule();
//    }

}
