//package com.wud.cloud.service;
//
//
//import com.wud.cloud.Dto.User;
//import com.wud.cloud.service.hystric.ServiceRibbonHystric;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
///**
// * 消费者再通过消费者去访问服务
// * */
//@FeignClient(value = "SERVICE-RIBBON",fallback = ServiceRibbonHystric.class)
//public interface ServiceRibbon {
//
//    @RequestMapping(value = "/ribbon/user",method = RequestMethod.GET)
//    List<User> userList();
//}
//
