package com.wud.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer8760Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8760Application.class, args);
    }

}
