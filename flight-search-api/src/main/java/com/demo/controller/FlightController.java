package com.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Flight;
import com.demo.service.FlightService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@RestController
public class FlightController {

	@Autowired
	private FlightService flightService;

	@RequestMapping(value = "/getFlights", method = RequestMethod.GET)
	public List<Flight> getFlights() throws Exception {
		
		return flightService.getAllFlights();
	}
	
	//REST call to get autofill data
	@RequestMapping(value = "/getAutofill", method = RequestMethod.GET)
	public List<String> getAutofill(@RequestParam(value = "site") String site) throws Exception {
		
		List<Flight> flights = flightService.getAllFlights();
		Iterator<Flight> flightIt = flights.iterator();
		
		List<String> stationList = new ArrayList<>();
		
		while(flightIt.hasNext()) {
			
			Flight flight = flightIt.next();
			
			if(flight != null) {
				
				if(flight.getOrigin_full_name() != null && site.equals("origin")) {
					
					stationList.add(flight.getOrigin_full_name());
				}
				
				else if(flight.getDestination_full_name() != null && site.equals("destination")) {
					
					stationList.add(flight.getDestination_full_name());
					
				}								
			}							
		}
				
		return removeDuplicates(stationList);
	}
	
   public List<String> removeDuplicates(List<String> sites){
	   
	   Set<String> set = new HashSet<>(sites);
	   sites.clear();
	   sites.addAll(set);
	   
		return sites;
   }

	@RequestMapping(value = "/getFlights", method = RequestMethod.POST)
	public void addFlight() throws IOException, ParseException {
		
		List<Flight> flightData = scanCrvFile();
		Iterator<Flight> flightDataIt = flightData.iterator();
		
		while (flightDataIt.hasNext()) {
			
			Flight flight = flightDataIt.next();
			
			flightService.addFlights(flight);
			
		}							
	}
	

	public static List<Flight> scanCrvFile() throws IOException, ParseException {
		// open file input stream
		Resource resource = new ClassPathResource("data_flights.csv");
		
		BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));

		// read file line by line
		String line = null;
		Scanner scanner = null;
		int index = 0;
		
		List<Flight> flightList = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			
			Flight flight = new Flight();
			
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
						
			//scanner.nextLine();
				
			while (scanner.hasNext()) {
										
				String data = scanner.next();
										
				 if (index == 0 && !data.equalsIgnoreCase("id")){
					 
					 flight.setId(Integer.parseInt(data));  
				 } 
				 				 
				 else if (index == 1 && !data.equalsIgnoreCase("Created_at"))
				 flight.setCreated_at(stringToDate(data)); 
				 
				 else if (index == 2 && !data.equalsIgnoreCase("Updated_at"))
				 flight.setUpdated_at(stringToDate(data)); 
				 
				 else if (index == 3 && !data.equalsIgnoreCase("Flight_identifier"))
				 flight.setFlight_identifier(data); 
				 
				 else if (index == 4 && !data.equalsIgnoreCase("Flt_num"))
				 flight.setFlt_num(Integer.parseInt(data)); 
				
				 else if (index == 5 && !data.equalsIgnoreCase("Scheduled_origin_gate"))
				 flight.setScheduled_origin_gate(data); 
				 
				 else if (index == 6 && !data.equalsIgnoreCase("Scheduled_destination_gate"))
				 flight.setScheduled_destination_gate(data); 
				 
				 else if (index == 7 && !data.equalsIgnoreCase("Out_gmt"))
				 flight.setOut_gmt(stringToDate(data)); 
				 
				 else if (index == 8 && !data.equalsIgnoreCase("In_gmt"))
				 flight.setIn_gmt(stringToDate(data)); 
				 
				 else if (index == 9 && !data.equalsIgnoreCase("Off_gmt"))
				 flight.setOff_gmt(stringToDate(data)); 
				 
				 else if (index == 10 && !data.equalsIgnoreCase("On_gmt"))
				 flight.setOn_gmt(stringToDate(data)); 
				 
				 else if (index == 11 && !data.equalsIgnoreCase("Destination"))
				 flight.setDestination(data); 
				 
				 else if (index == 12 && !data.equalsIgnoreCase("Origin")) 
				flight.setOrigin(data);
				 
				 else if (index == 13 && !data.equalsIgnoreCase("Destination_full_name")) 
				flight.setDestination_full_name(data); 
				 
				 else if (index == 14 && !data.equalsIgnoreCase("Origin_full_name")) 
				flight.setOrigin_full_name(data); 
				 
				 else
				 System.out.println("invalid data::" + data);
				 
				index++;
			}

			index = 0;
			flightList.add(flight);
		}
		
		flightList.remove(0);
		
		//close reader
		reader.close();
		
		return flightList;
		
	}
	
	//Convert String to Date	
	public static Date stringToDate(String dateString) throws ParseException {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(dateString);
		
		return date;	
	}

}
