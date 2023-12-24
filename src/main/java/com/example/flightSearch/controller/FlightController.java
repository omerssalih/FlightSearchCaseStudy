package com.example.flightSearch.controller;

import com.example.flightSearch.model.Airports;
import com.example.flightSearch.model.Flights;
import com.example.flightSearch.service.AirportService;
import com.example.flightSearch.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/flight")
public class FlightController {

    private final FlightService flightService;
    private final AirportService airportService;



    @PostMapping
    public Flights createFlight(@RequestBody Flights flights) {
        return flightService.createFlight(flights);
    }

    @GetMapping
    public List<Flights> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(path = "searchApi")
    public List<Flights> getFlights(
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) LocalDate departureTime,
            @RequestParam(required = false) LocalDate returnTime
    ) {

        Airports departureAirport = airportService.getByAirportId(departureAirportId);
        Airports arrivalAirport = airportService.getByAirportId(arrivalAirportId);

        if (returnTime != null) {
            return flightService.getFlightsByDepartureAndReturn(departureAirport, arrivalAirport, departureTime, returnTime);
        }else{
            return flightService.getFlightsByDeparture(departureAirport, arrivalAirport, departureTime);
        }
    }


    @DeleteMapping("/{ID}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long ID) {
        boolean isDeleted = flightService.deleteFlightById(ID);
        if (isDeleted) {
            return new ResponseEntity<>("Flight with ID " + ID + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight with ID " + ID + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{ID}")
    public ResponseEntity<String> updateFlight(@PathVariable Long ID, @RequestBody Flights flights) {
        boolean isUpdated = flightService.updateFlight(ID, flights);

        if (isUpdated) {
            return new ResponseEntity<>("Flight with ID " + ID + " has been updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight with ID " + ID + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}
