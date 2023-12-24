package com.example.flightSearch.controller;

import com.example.flightSearch.model.Airports;
import com.example.flightSearch.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/airport")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airports> getAllAirport() {
        return airportService.getAllAirports();
    }

    @PostMapping
    public Airports createAirport(@RequestBody Airports airports) {
        return airportService.createAirport(airports);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long ID) {
        boolean isDeleted = airportService.deleteAirportById(ID);
        if (isDeleted) {
            return new ResponseEntity<>("Airport with ID " + ID + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Airport with ID " + ID + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{ID}")
    public ResponseEntity<String> updateAirport(@PathVariable Long ID, @RequestBody Airports airports) {
        boolean isUpdated = airportService.updateAirport(ID, airports);

        if (isUpdated) {
            return new ResponseEntity<>("Airport with ID " + ID + " has been updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Airport with ID " + ID + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
