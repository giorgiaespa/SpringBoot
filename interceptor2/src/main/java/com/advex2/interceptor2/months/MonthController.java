package com.advex2.interceptor2.months;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/months")
public class MonthController {
    @Autowired
    private MonthService monthService;

    @GetMapping("")
    private Month month(HttpServletRequest request) {
        return (Month) request.getAttribute("englishName");
    }
}

