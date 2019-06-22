//package com.wud.cloud.service.hystric;
//
//
//import com.wud.cloud.Dto.User;
//import com.wud.cloud.service.ServiceHi;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class ServiceHiHystric implements FallbackFactory<ServiceHi> {
//
//    @Override
//    public ServiceHi create(Throwable throwable) {
//        return new ServiceHi() {
//            @Override
//            public String sayHiFromClientOne(String name) {
//                return  "sorry 服务降级"+name;
//            }
//
//            @Override
//            public List<User> userList() {
//                return null;
//            }
//        };
//    }
//}