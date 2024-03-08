package com.advex4.customQ2.flight;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository <Flight, Long> {
    @Query(value = "SELECT * FROM Flight", nativeQuery = true)
    List<Flight> findAllFlights();

    @Query(value = "SELECT * FROM Flight ", nativeQuery = true)
    Page <Flight> findAllByPage(Pageable pageable);

    @Query(value = "SELECT * FROM Flight WHERE status = :status1 OR status = :status2 ", nativeQuery = true)
    List<Flight> findByStatus(@Param("status1") String status1,@Param("status2") String status2);
}
