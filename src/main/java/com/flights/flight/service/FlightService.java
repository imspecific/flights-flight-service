package com.flights.flight.service;

import com.flights.flight.exception.FlightNotFoundException;
import com.flights.flight.exception.UserNotFoundException;
import com.flights.flight.feignclient.UserFeignClient;
import com.flights.flight.model.Flight;
import com.flights.flight.repository.FlightRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private UserFeignClient userFeignClient;

    private static final String USER_SERVICE = "poc-user-service";

    @CircuitBreaker(name = "USER_SERVICE", fallbackMethod = "userServiceFallBack")
    public Flight addFlight(Flight flight, String userName, String password, String userType) {
        if (userFeignClient.validUser(userName, password, userType)) {
            if (userType.equals("admin")) {
                return flightRepo.save(flight);
            } else
                throw new UserNotFoundException("You are not allowed to add a flight.");
        } else
            throw new FlightNotFoundException("Declined..");
    }


    public List<Flight> allFlights(String userName, String password) {
        if (userFeignClient.validUser(userName, password)) {
            List<Flight> flights = flightRepo.findAll();
            if (flights.isEmpty()) {
                throw new FlightNotFoundException("No flights.");
            } else {
                return flights;
            }
        } else {
            throw new FlightNotFoundException("Declined.");
        }
    }


    public Flight flightById(Integer flightId, String userName, String password) {
        if (userFeignClient.validUser(userName, password)) {
            Flight f = flightRepo.findFlightById(flightId);
            if (f == null)
                throw new FlightNotFoundException("Not found");
            else
                return f;
        } else throw new FlightNotFoundException("You are not authenticated. Please try again !!");
    }


    public Boolean deleteFlightById(Integer flightId, String userName, String password, String userType) {
        if (userFeignClient.validUser(userName, password, userType)) {
            if (userType.equals("admin")) {
                flightRepo.deleteById(flightId);
                return true;
            } else
                throw new UserNotFoundException("You are not allowed to cancel a flight.");
        } else
            throw new FlightNotFoundException("You are not authenticated. Please try again !!");
    }


    public List<Flight> getFlightsBySourceAndDestination(String source, String destination, String userName, String password) {
        if (userFeignClient.validUser(userName, password)) {
            return flightRepo.getFlightsBySourceAndDestination(source, destination);
        } else
            throw new FlightNotFoundException("Flights not found");
    }

    public String updateFlightStatus(Integer flightId, Integer availSeats) {
        flightRepo.updateFlightStatusById(flightId, availSeats);
        return "Updated.";
    }

    public Flight userServiceFallBack(Exception ex) {
        throw new UserNotFoundException("User Service is currently down...");
    }

}
