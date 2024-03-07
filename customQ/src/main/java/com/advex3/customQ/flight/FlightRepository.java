package com.advex3.customQ.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
   @Query(value = "SELECT * FROM flights", nativeQuery = true)
    List<FlightEntity> findAllFlights();
}
