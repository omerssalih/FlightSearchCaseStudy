package com.example.flightSearch.service;

import com.example.flightSearch.model.Airports;
import com.example.flightSearch.model.Flights;
import com.example.flightSearch.repository.AirportRepository;
import com.example.flightSearch.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airports> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airports getByAirportId(Long ID){
        return airportRepository.findById(ID).orElseThrow();
    }

    public Airports createAirport(Airports airports) {
        return airportRepository.save(airports);
    }

    public boolean deleteAirportById(Long ID) {
        Optional<Airports> optionalAirports = airportRepository.findById(ID);
        if (optionalAirports.isPresent()) {
            airportRepository.deleteById(ID);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateAirport(Long ID, Airports airports) {
        Optional<Airports> optionalAirports = airportRepository.findById(ID);

        if (optionalAirports.isPresent()) {
            Airports existingAirport = optionalAirports.get();
            existingAirport.setCity((airports.getCity()));
            airportRepository.save(existingAirport);
            return true;
        } else {
            return false;
        }
    }
}
