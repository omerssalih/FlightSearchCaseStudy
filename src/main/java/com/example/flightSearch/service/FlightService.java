package com.example.flightSearch.service;

import com.example.flightSearch.model.Airports;
import com.example.flightSearch.model.Flights;
import com.example.flightSearch.repository.AirportRepository;
import com.example.flightSearch.repository.FlightRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;


    public Flights createFlight(Flights flights) {
        return flightRepository.save(flights);
    }

    public List<Flights> getAllFlights() {
        return flightRepository.findAll();
    }

    public boolean deleteFlightById(Long ID) {
        Optional<Flights> optionalFlight = flightRepository.findById(ID);
        if (optionalFlight.isPresent()) {
            flightRepository.deleteById(ID);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateFlight(Long ID, Flights flights) {
        Optional<Flights> optionalFlight = flightRepository.findById(ID);

        if (optionalFlight.isPresent()) {
            Flights existingFlight = optionalFlight.get();
            existingFlight.setDepartureAirport(airportService.getByAirportId(ID));
            existingFlight.setArrivalAirport(airportService.getByAirportId(ID));
            existingFlight.setDepartureTime(flights.getDepartureTime());
            existingFlight.setReturnTime(flights.getReturnTime());
            existingFlight.setPrice(flights.getPrice());

            flightRepository.save(existingFlight);
            return true;
        } else {
            return false;
        }
    }

    public List<Flights> getFlightsByDeparture(Airports departureAirport, Airports arrivalAirport, LocalDate departureTime) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(
                departureAirport,
                arrivalAirport,
                departureTime);
    }

    public List<Flights> getFlightsByDepartureAndReturn(Airports departureAirport, Airports arrivalAirport, LocalDate departureTime, LocalDate returnTime) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTimeAndReturnTime(
                departureAirport,
                arrivalAirport,
                departureTime,
                returnTime);
    }

}
