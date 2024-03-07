package com.advex2.interceptor2.months;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthService {
    @Autowired
    private MonthRepository monthRepository;
}
