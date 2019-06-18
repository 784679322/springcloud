package com.wud.servicehi.controller;


import com.wud.cloud.Dto.User;
import com.wud.cloud.service.ServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HiController {

    @Autowired
    ServiceHi userService;
    @Autowired
    DiscoveryClient client;


    @RequestMapping(value = "/name")
    public String getname(@RequestParam String name  ) {
        System.out.println("----->Hi2  getName");
        return "这是第二个服务"+userService.getName(name);
    }

    @RequestMapping(value = "/list")
    public List<User> getList(  ) {
        System.out.println("----->Hi2  getList");
        return userService.userList();
    }

//    @Autowired
//    DiscoveryClient client;
    @RequestMapping(value = "/discover",method = RequestMethod.GET)
    public Object discover(  ) {
        List<String> list=client.getServices();
        System.out.println("**  **  ** **  **"+list);
        List<ServiceInstance> serList=client.getInstances("SERVICE-HI-8764");

        for(ServiceInstance element:serList){
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return this.client;
    }
}
