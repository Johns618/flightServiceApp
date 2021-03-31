package com.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Flight;
import com.demo.repository.FlightRepository;
import com.demo.service.FlightService;
import org.springframework.util.Assert;

@Service
public class FlightServiceImpl implements FlightService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FlightRepository flightRepository;

	@Override
	public void addFlights(Flight flight) {
		Assert.notNull(flight, "flight must not be null");
		flightRepository.save(flight);
	}

	@Override
	public List<Flight> getAllFlights() {
		Assert.notNull(flightRepository.getAllFlights(), "flight list must not be null");
		return flightRepository.getAllFlights();
	}

}
