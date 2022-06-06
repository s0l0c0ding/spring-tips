package dev.solocoding.customannotaions.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.solocoding.customannotaions.config.OurCustomService;

@OurCustomService
public class DemoServiceImpl  implements DemoService {

    private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public void print() {
       log.info("""
               This Message is from:
                DemoService
               """); 
    }
    
}
