package com.wud.cloudhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author wud
 * @Description: TODO
 * @date 2019/6/2022:06
 */
@SpringBootApplication
@EnableHystrixDashboard


public class CloudHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixApplication.class, args);
    }

}
