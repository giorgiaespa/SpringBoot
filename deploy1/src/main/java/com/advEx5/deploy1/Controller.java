package com.advEx5.deploy1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
public class Controller {
    @Autowired
    private Environment environment;
    @GetMapping("/get")
    private String printVar(){
        return environment.getProperty("myProperty.devName");
    }
}
