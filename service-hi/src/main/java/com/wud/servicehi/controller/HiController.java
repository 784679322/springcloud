package com.wud.servicehi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HiController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/hihi")
    public String hi(@RequestParam String name  ) {
        return name;
    }
    @RequestMapping(value = "/user")
    public List<User> sayHi(  ) {
        System.out.println(userService.selectById().size());

        return userService.selectById();
    }
}
