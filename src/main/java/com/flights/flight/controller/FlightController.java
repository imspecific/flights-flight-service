package com.flights.flight.controller;

import com.flights.flight.model.Flight;
import com.flights.flight.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("poc-flight-service")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // poc-flight-service/add-flight/admin/admin/admin
    @PostMapping("/add-flight/{userName}/{password}/{userType}")
    public Flight addFlight(@RequestBody Flight flight, @PathVariable String userName, @PathVariable String password, @PathVariable String userType) {
        log.info("poc-flight-service add flight controller executed.");
        return flightService.addFlight(flight, userName, password, userType);
    }

    // poc-flight-service/all-flights/user1/password
    @GetMapping(value = "/all-flights/{userName}/{password}")
    public List<Flight> allFlights(@PathVariable String userName, @PathVariable String password) {
        log.info("poc-flight-service all flights controller executed.");
        return flightService.allFlights(userName, password);
    }

    // poc-flight-service/find-flight-by-id/201/user1/password
    @GetMapping(value = "/find-flight-by-id/{flightId}/{userName}/{password}")
    public Flight flightById(@PathVariable Integer flightId, @PathVariable String userName, @PathVariable String password) {
        log.info("poc-flight-service flight by id controller is executed.");
        return flightService.flightById(flightId, userName, password);
    }

    // poc-flight-service/cancel-flight-by-id/202/admin/admin/admin
    @DeleteMapping(value = "/cancel-flight-by-id/{flightId}/{userName}/{password}/{userType}")
    public String cancelFlightById(@PathVariable Integer flightId, @PathVariable String userName, @PathVariable String password, @PathVariable String userType) {
        if (flightService.deleteFlightById(flightId, userName, password, userType))
            return "Flight canceled.";
        else
            return "Some error occurred.";
    }

    //	poc-flight-service/flights-by-source-destination/Lucknow/Hyderabad/user1/password
    @GetMapping(value = "/flights-by-source-destination/{source}/{destination}/{userName}/{password}")
    public List<Flight> getFlightsBySourceAndDestination(@PathVariable String source, @PathVariable String destination, @PathVariable String userName, @PathVariable String password) {
        log.info("poc-flight-service flight by source and destination");
        return flightService.getFlightsBySourceAndDestination(source, destination, userName, password);
    }

    // poc-flight-service/update-flight-status/201/15
    @PutMapping(value = "/update-flight-status/{flightId}/{availSeats}")
    public String updateFlightStatus(@PathVariable Integer flightId, @PathVariable Integer availSeats) {
        log.info(flightId + " " + availSeats);
        return flightService.updateFlightStatus(flightId, availSeats);
    }

}
