package com.wud.servicefeign;


import com.wud.servicefeign.controller.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }

    @Override
    public List<User> userList() {
        return null;
    }
}