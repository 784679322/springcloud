package com.wud.servicefeign.controller;



import com.wud.cloud.Dto.User;
import com.wud.servicefeign.service.ServiceHi;
import com.wud.servicefeign.service.ServiceRibbon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HiController {


    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    ServiceHi schedualServiceHi;
    @Autowired
    ServiceRibbon ribbonService;

    @GetMapping(value = "/name")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne( name );
    }

    @GetMapping(value = "/list")
    public List<User> list(  ) {
        List<User> list=schedualServiceHi.userList(  );
        return list;
    }
    @GetMapping(value = "/error")
    public String error(  ) {
        String s=schedualServiceHi.error(  );
        return s;
    }

    @GetMapping(value = "/toribbon")
    public List<User> toribbon(  ) {
        return ribbonService.userList(  );
    }


}
