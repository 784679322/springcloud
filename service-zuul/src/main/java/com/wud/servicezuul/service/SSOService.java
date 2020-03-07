package com.wud.servicezuul.service;


import com.wud.cloud.Dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "SERVICE-SSO")
//@FeignClient(value = "SERVICE-HI")
public interface SSOService {


    @RequestMapping(value = "/sso/list",method = RequestMethod.GET)
    List<User> userList();

}

