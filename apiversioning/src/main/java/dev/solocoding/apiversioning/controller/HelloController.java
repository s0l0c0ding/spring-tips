package dev.solocoding.apiversioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.solocoding.apiversioning.service.HelloService;

@RestController
public class HelloController {
    
    private final HelloService helloService;
    
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public  String sayHello(){
        return helloService.sayHello();
    }
}
