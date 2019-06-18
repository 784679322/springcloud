package com.wud.servicefeign.service.hystric;


import com.wud.cloud.Dto.User;
import com.wud.servicefeign.service.SchedualServiceHi;
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