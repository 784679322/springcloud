package com.wud.cloud.service;


import com.wud.cloud.Dto.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ServiceHiHystric implements FallbackFactory<ServiceHi> {
    @Override
    public ServiceHi create(Throwable throwable) {
        return new ServiceHi() {
            @Override
            public String getName(String name) {
                return "服务降级";
            }

            @Override
            public List<User> userList() {
                return null;
            }
        };
    }
}