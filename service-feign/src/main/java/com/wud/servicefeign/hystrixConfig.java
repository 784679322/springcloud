package com.wud.servicefeign;

import com.wud.cloud.service.hystric.ServiceHiHystric;
import com.wud.cloud.service.hystric.ServiceRibbonHystric;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wud
 * @Description: TODO
 * @date 2019/6/2023:09
 */
@Configuration

public class hystrixConfig {
        @Bean
        public ServiceHiHystric deptServiceFallBack(){
            return new ServiceHiHystric ();
        }
        @Bean
        public ServiceRibbonHystric sdsd(){
            return new ServiceRibbonHystric ();
        }
    }
