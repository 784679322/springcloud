package com.wud.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wud
 * @Description: TODO
 * @date 2019/6/167:36
 */
@Configuration
public class MySelfRule  {
    @Bean
    public IRule myRule(){
       return new My_RandomRule();
    }
}
