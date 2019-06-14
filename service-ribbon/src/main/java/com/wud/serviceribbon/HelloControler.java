package com.wud.serviceribbon;

import com.wud.serviceribbon.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ribbon")
public class HelloControler {

    @Autowired
    HelloService helloService;
    @Autowired
    RestTemplate restTemplate;

    private  static String REST_URL_PREFIX="http://SERVICE-HI";

    @RequestMapping(value = "/discover")
    public Object get(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/discover",Object.class);
    }

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }


}

