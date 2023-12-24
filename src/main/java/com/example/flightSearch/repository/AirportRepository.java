package com.example.flightSearch.repository;

import com.example.flightSearch.model.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airports, Long> {

}
