package com.advex4.customQ2.flight;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/readAll")
    public List<Flight> readAll() {
        return flightService.findAll();
    }

    @GetMapping("/getFlightList")
    public List<Flight> readFlightList(@RequestParam(defaultValue = "100") int n) {
        return flightService.provisioning(n);
    }
    @GetMapping("/getFlightList/pages")
    public Page<Flight> flightByPages() {
        return flightService.pagination();
    }
    @GetMapping("/onTime")
    public List<Flight> showOnTime() {
        return flightService.findOnTimeFlights();
    }

    @GetMapping("/customStatus")
    public List<Flight> getCustomStatusFlights(@RequestParam(name = "p1") Status p1, @RequestParam(name = "p2") Status p2) {
        return flightService.getCustomStatusFlights(p1, p2);
    }

}
