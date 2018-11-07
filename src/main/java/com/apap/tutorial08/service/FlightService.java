package com.apap.tutorial08.service;

import java.util.Optional;

import com.apap.tutorial08.model.FlightModel;


/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    
    void deleteByFlightNumber(String flightNumber);

    Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);
}