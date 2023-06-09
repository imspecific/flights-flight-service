package com.flights.flight.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "poc_flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer flightId;

    private String source;
    private String destination;
    private Integer availSeats;
    private String tClass;
    private String departureDate;
    private String departureTime;
    private double fare;
}
