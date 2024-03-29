package com.flights.flight;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.flights.flight.feignclient")
public class PocFlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocFlightServiceApplication.class, args);
		log.info("POC-Flight Service is running..");
	}

}
