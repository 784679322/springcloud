package com.wud.servicezuul.controller;

import com.wud.cloud.Dto.User;

import com.wud.cloud.service.ServiceHi;
import com.wud.servicezuul.service.SSOService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    SSOService ssoService;



    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello ,spring security!";
    }

    @RequestMapping("/tesi")
    @ResponseBody
    String tesi() {
        List<User> list =  ssoService.userList();
        return "Hello ,spring security!";
    }


}