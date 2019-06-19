package com.wud.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    private  static String REST_URL_PREFIX="http://SERVICE-HI";

    @HystrixCommand(fallbackMethod = "getNameError")
    public String getName(String name) {
        return restTemplate.getForObject(REST_URL_PREFIX+"/name?name="+name,String.class);
    }

    public String getNameError(String name) {
        return "hi,获取name失败"+name+",sorry,error!";
    }

    @HystrixCommand(fallbackMethod = "getListError")
    public Object getList() {
        return restTemplate.getForObject(REST_URL_PREFIX+"/list",Object.class);
    }

    public Object getListError() {
        return "获取List失败了";
    }

    public Object getDiscover() {
        return restTemplate.getForObject(REST_URL_PREFIX+"/discover",Object.class);
    }


}
