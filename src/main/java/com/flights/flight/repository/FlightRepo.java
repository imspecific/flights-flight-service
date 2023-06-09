package com.flights.flight.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.flights.flight.model.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer> {
	
	@Query(value="select * from poc_flight p where p.flight_id=?1", nativeQuery = true)
	Flight findFlightById(@PathVariable Integer flightId);
	
	@Query(value="select * from poc_flight p where p.source=?1 and p.destination=?2", nativeQuery = true)
	List<Flight> getFlightsBySourceAndDestination(@PathVariable String source, @PathVariable String destination);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE poc_flight p set p.avail_seats=?2 WHERE p.flight_id=?1", nativeQuery = true)
	void updateFlightStatusById(@PathVariable Integer flightId, @PathVariable Integer availSeats);
}
