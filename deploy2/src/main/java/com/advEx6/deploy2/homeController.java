package com.advEx6.deploy2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mathOp")
public class homeController {

    @GetMapping("/sum")
    private int add(@RequestParam int num, int num2){
        return num + num2;
    }
}
