package com.example.flightSearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flights {
    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_sequence")
    private Long ID;
    /*private String departureAirport;
    private String arrivalAirport;*/
    private LocalDate departureTime;
    private LocalDate returnTime;
    private int price;

    @ManyToOne
    private Airports departureAirport;
    @ManyToOne
    private Airports arrivalAirport;




}
