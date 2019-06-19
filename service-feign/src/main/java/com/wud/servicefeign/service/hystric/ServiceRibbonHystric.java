package com.wud.servicefeign.service.hystric;


import com.wud.cloud.Dto.User;
import com.wud.servicefeign.service.ServiceRibbon;
import com.wud.servicefeign.service.ServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceRibbonHystric implements ServiceRibbon {

    @Autowired
    ServiceHi schedualServiceHi;
    @Override
    public List<User> userList() {
        System.out.println("-------->ribbon服务出错了！！");
        System.out.println(schedualServiceHi.userList().get(0));
        return schedualServiceHi.userList();
    }
}