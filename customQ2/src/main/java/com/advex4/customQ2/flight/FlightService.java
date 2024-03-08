package com.advex4.customQ2.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private String generateRandomString(Random random) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char aChar = chars[random.nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    private Status generateRandomStatus() {
        return Status.values()[new Random().nextInt(Status.values().length)];
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public List<Flight> provisioning(int n) {
        Random random = new Random();
        List <Flight> flights = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            Flight flight = new Flight();
            flight.setDescription("Flight number: " + n);
            flight.setFromAirport(generateRandomString(random));
            flight.setToAirport(generateRandomString(random));
            flight.setStatus(generateRandomStatus());
            flights.add(flight);
        }
        flightRepository.saveAllAndFlush(flights);
        return flights;
    }
    public Page<Flight> pagination() {
        Pageable pageSorting = PageRequest.of(0, 10, Sort.by("fromAirport").ascending());
        return flightRepository.findAllByPage(pageSorting);
    }

    public List<Flight> findOnTimeFlights() {
        List<Flight> allFlights = flightRepository.findAllFlights();
        List<Flight> onTimeFlights = new ArrayList<>();

        for (Flight f : allFlights) {
            if (f.getStatus() == Status.ONTIME) {
                onTimeFlights.add(f);
            }
        }
        return onTimeFlights;
    }


    public List<Flight> getCustomStatusFlights(Status p1, Status p2) {
        return flightRepository.findByStatus(p1.name().toString(), p2.name().toString());
    }
}
