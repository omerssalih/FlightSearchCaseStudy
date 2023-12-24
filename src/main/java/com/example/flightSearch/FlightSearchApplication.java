package com.example.flightSearch;

import com.example.flightSearch.model.Airports;
import com.example.flightSearch.service.AirportService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlightSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner initializeData(AirportService airportService) {
		return args -> {
			addDefaultAirports(airportService);
		};
	}

	private void addDefaultAirports(AirportService airportService) {
		for (int i = 1; i <= 30; i++) {
			Airports airports = new Airports( Integer.toUnsignedLong(i) ,"MockCity" + i);
			airportService.createAirport(airports);
		};
	}

}
