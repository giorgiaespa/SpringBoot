package com.advex3.customQ.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    public FlightRepository flightRepository;
    @Autowired
    public FlightEntity flightEntity;
    private int numFlights = 50;
    private Status defaultStatus = Status.ONTIME;

    private String generateRandomString(Random random) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char aChar = chars[random.nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public void provisioning() {
        Random random = new Random();
        for (int i = 0; i < numFlights; i ++) {
            FlightEntity flight = new FlightEntity();
            flight.setDescription("Inbound flights");
            flight.setFromAirport(generateRandomString(random));
            flight.setToAirport(generateRandomString(random));
            flight.setStatus(defaultStatus);
            flightRepository.save(flight);
        }
    }

    public List <FlightEntity> getAllFlights() {
        return flightRepository.findAllFlights();
    }

}

