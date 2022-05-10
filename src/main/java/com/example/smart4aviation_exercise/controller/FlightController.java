package com.example.smart4aviation_exercise.controller;

import com.example.smart4aviation_exercise.dto.response.FlightWeightDto;
import com.example.smart4aviation_exercise.dto.response.FlightsWithBaggageDto;
import com.example.smart4aviation_exercise.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping(path = "/weights")
    public ResponseEntity<FlightWeightDto> getFlightWeightByFlightNumberAndDate(
            @RequestParam("flightNumber") Long flightNumber,
            @RequestParam("date") String date
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.getFlightWeightByFlightNumberAndDate(flightNumber, date));
    }

    @GetMapping(path = "/airports")
    public ResponseEntity<FlightsWithBaggageDto> getFlightsByIATAAirportCodeAndDAte(
            @RequestParam("airportCode") String airportCode,
            @RequestParam("date") String date
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.getFlightsByIATAAirportCodeAndDate(airportCode, date));
    }

}
