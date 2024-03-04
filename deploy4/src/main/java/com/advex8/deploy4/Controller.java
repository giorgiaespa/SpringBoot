package com.advex8.deploy4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class Controller {
    @Autowired
    private Environment environment;
    @GetMapping("/user")
    private String printVar(){
        return environment.getProperty("property.welcomeMsg") + " !";
    }
}