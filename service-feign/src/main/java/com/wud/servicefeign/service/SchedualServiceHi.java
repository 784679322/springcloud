package com.wud.servicefeign.service;


import com.wud.cloud.Dto.User;
import com.wud.servicefeign.service.hystric.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "SERVICE-HI",fallback = SchedualServiceHiHystric.class)
//@FeignClient(value = "SERVICE-HI")
public interface SchedualServiceHi {
    @RequestMapping(value = "/hihi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    List<User> userList();
}

