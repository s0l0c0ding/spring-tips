package dev.solocoding.apiversioning.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "server.servlet.context-path", havingValue = "/v2")
public class HelloServiceImplTwo implements HelloService  {

    @Override
    public String sayHello() {
        return "Hello from v2";
    }
    
}
