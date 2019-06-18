package com.wud.servicehystrix.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wud.cloud.Dto.User;

import com.wud.servicehystrix.service.UserService;
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
    UserService userService;
    @Autowired
    DiscoveryClient client;


    @RequestMapping(value = "/hihi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(@RequestParam String name  ) {

        if(name.equals("1")){
            throw  new RuntimeException("_____??没有");
        }
        return name;
    }

    public String hiError(@RequestParam String name ){
        return "这是Hystrix返回的值";
    }


    @RequestMapping(value = "/user")
    public List<User> sayHi(  ) {
        System.out.println(userService.selectById().size());

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
