package com.advex3.customQ.flight;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightService flightService;

    @PostMapping("/provision")
    public void createFlights() {
        flightService.provisioning();
    }

    @GetMapping("getAll")
    public List<FlightEntity> readAll() {
        return flightService.getAllFlights();
    }

}