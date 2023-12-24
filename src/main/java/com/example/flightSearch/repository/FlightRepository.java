package com.example.flightSearch.repository;

import com.example.flightSearch.model.Airports;
import com.example.flightSearch.model.Flights;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Long> {

    /*Optional<List<Flights>> findByDepartureAirport(String departureAirport);
    Optional<List<Flights>> findByArrivalAirport(String arrivalAirport);*/
    Optional<List<Flights>> findByDepartureTime(LocalDate departureTime);
    Optional<List<Flights>> findByReturnTime(LocalDate returnTime);
    List<Flights> findByDepartureAirportAndArrivalAirportAndDepartureTime(Airports departureAirport, Airports arrivalAirport, LocalDate departureTime);

    List<Flights> findByDepartureAirportAndArrivalAirportAndDepartureTimeAndReturnTime(Airports departureAirport, Airports arrivalAirport, LocalDate departureTime, LocalDate returnTime);
}
