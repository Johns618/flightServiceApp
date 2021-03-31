package com.demo.service;

import java.util.List;

import com.demo.model.Flight;

public interface FlightService {
	
	public void addFlights(Flight flight);

	public List<Flight> getAllFlights();
}
