//package com.wud.cloud.service.hystric;
//
//
//import com.wud.cloud.Dto.User;
//import com.wud.cloud.service.ServiceHi;
//import com.wud.cloud.service.ServiceRibbon;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class ServiceRibbonHystric implements ServiceRibbon {
//
//    @Autowired
//    ServiceHi schedualServiceHi;
//    @Override
//    public List<User> userList() {
//        System.out.println("-------->ribbon服务出错了！！");
//        System.out.println(schedualServiceHi.userList().get(0));
//        return schedualServiceHi.userList();
//    }
//}