package com.advex9.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class IdkService {
    @Autowired
    private Environment environment;
    Logger logger = LoggerFactory.getLogger(IdkService.class);
    public double exp (double num1, double num2){
        logger.info("Starting calculation");
        double power = Math.pow(num1,num2);
        logger.info("Finishing calculation");
        return power;
    }
}
