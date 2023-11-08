package com.aaytugozkaya.springsecurity.res;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {


    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World v222s";
    }
}
