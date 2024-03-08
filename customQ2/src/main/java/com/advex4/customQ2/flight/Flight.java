package com.advex4.customQ2.flight;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

enum Status{
    ONTIME,
    DELAYED,
    CANCELLED
}

@Entity
@Table(name= "flights")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String description;

    private String fromAirport;

    private String toAirport;

    private Status status;
}
