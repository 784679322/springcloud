package com.wud.servicehi.controller;


import com.wud.cloud.Dto.User;
import com.wud.servicehi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HiController {

    @Autowired
    UserService userService;
    @Autowired
    DiscoveryClient client;


    @RequestMapping(value = "/name")
    public String getName(@RequestParam String name  ) {
        System.out.println("----->Hi1  getName");
        return "这是一个服务"+name;
    }

    @RequestMapping(value = "/list")
    public List<User> getList(  ) {
        System.out.println("----->Hi1  getList");
        return userService.selectById();
    }

//    @Autowired
//    DiscoveryClient client;
    @RequestMapping(value = "/discover",method = RequestMethod.GET)
    public Object discover(  ) {
        List<String> list=client.getServices();
        System.out.println("**  **  ** **  **"+list);
        List<ServiceInstance> serList=client.getInstances("SERVICE-HI-8763");
        for(ServiceInstance element:serList){
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }
        return this.client;
    }
}
