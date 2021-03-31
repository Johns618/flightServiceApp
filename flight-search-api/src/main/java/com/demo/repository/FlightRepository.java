package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	@Query("FROM Flight")
	public List<Flight> getAllFlights();
}
