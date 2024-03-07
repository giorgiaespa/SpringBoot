package com.advex3.customQ.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum Status{
 ONTIME,
 DELAYED,
 CANCELLED
}

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "flights")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String description;

    private String fromAirport;

    private String toAirport;

    private Status status;
}
