package com.wud.servicefeign.service;


import com.wud.cloud.Dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 消费者再通过消费者去访问服务
 * */
@FeignClient(value = "SERVICE-RIBBON")
public interface RibbonService {

    @RequestMapping(value = "/ribbon/user",method = RequestMethod.GET)
    List<User> userList();
}

