package com.wud.servicefeign.service.hystric;


import com.wud.cloud.Dto.User;
import com.wud.servicefeign.service.ServiceHi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceHiHystric implements ServiceHi {


            @Override
            public String sayHiFromClientOne(String name) {
                return  "sorry 服务降级"+name;
            }

            @Override
            public List<User> userList() {
                return null;
            }


}