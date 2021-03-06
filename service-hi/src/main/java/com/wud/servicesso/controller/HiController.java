package com.wud.servicesso.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wud.cloud.Dto.User;
import com.wud.servicesso.service.UserService;
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


    @RequestMapping(value = "/test")
    @HystrixCommand(fallbackMethod = "hiError")
    public String test(@RequestParam String name  ) {
        if(name.equals("1")){
            throw  new RuntimeException("name = 1 throw Exception, 调起服务端熔断器");
        }
        return name;
    }

    public String hiError(@RequestParam String name ){
        return "这是当 name=1 时, Hystrix熔断器返回的值";
    }


}
