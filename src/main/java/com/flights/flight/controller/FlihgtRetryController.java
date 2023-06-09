package com.flights.flight.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.retry.annotation.Retry;


@RestController
public class FlihgtRetryController {

	private static Logger logger = LoggerFactory.getLogger(FlihgtRetryController.class);
	
	@GetMapping(value="/flight-retryinfo")
	@Retry(name="myservice", fallbackMethod = "failureResponse")
	public String retryInfo() {
		logger.info("Flight retry controller call received.");
		return "Resilience retry info called.";
	}
	
	public String failureResponse(Exception ex) {
		return "From Retry: Flight Service is down.";
	}
}
