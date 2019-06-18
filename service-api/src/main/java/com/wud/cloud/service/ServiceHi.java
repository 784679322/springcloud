package com.wud.cloud.service;


import com.wud.cloud.Dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "SERVICE-HI",fallback = ServiceHiHystric.class)
//@FeignClient(value = "SERVICE-HI")
public interface ServiceHi {
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    String getName(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    List<User> userList();
}

