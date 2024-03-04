package com.advex7.deploy3;

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
        return "Hello " + environment.getProperty("firstProperty.devName") + "! code: " + environment.getProperty("secondProperty.authCode");
    }
}
