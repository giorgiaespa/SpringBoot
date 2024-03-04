package com.advex9.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IdkController {
    @Autowired
    private IdkService idkService;
    @Value("${customEnv.var1}")
    private double num1;
    @Value("${customEnv.var2}")
    private double num2;
    Logger logger = LoggerFactory.getLogger(IdkController.class);
    @GetMapping("/")
    private String welcome(){
        logger.info("just a welcome message");
        return "Welcome!";
    }
    @GetMapping("/exp")
    private double power (){
        logger.info("Starting calculation");
        logger.info("Finishing calculation");
        return idkService.exp(num1,num2);
    }

    @GetMapping("/get-errors")
    private Error errors () {
        logger.error("ERROR detected");
        return new Error("Something went wrong!");
    }
}
