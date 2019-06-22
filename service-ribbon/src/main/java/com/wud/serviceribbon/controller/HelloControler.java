package com.wud.serviceribbon.controller;

import com.wud.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@RequestMapping("/ribbon")
public class HelloControler {

    @Autowired
    HelloService helloService;
    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value = "/name")
    public String hi(@RequestParam String name) {
        return helloService.getName( name );
    }

    @RequestMapping(value = "/list")
    public Object user() {
        return helloService.getList(  );
    }

    @RequestMapping(value = "/discover")
    public Object get(){
        return  helloService.getDiscover();
    }


}

