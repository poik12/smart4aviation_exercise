package com.example.smart4aviation_exercise.service;

import com.example.smart4aviation_exercise.dto.response.FlightWeightDto;
import com.example.smart4aviation_exercise.dto.response.FlightsWithBaggageDto;
import com.example.smart4aviation_exercise.entity.enums.ArrivalAirportIATACodeEnum;
import com.example.smart4aviation_exercise.entity.enums.DepartureAirportIATACodeEnum;
import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import com.example.smart4aviation_exercise.exception.FlightException;
import com.example.smart4aviation_exercise.exception.message.InvalidFlightEnum;
import com.example.smart4aviation_exercise.mapper.FlightMapper;
import com.example.smart4aviation_exercise.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final LoadService loadService;
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightWeightDto getFlightWeightByFlightNumberAndDate(Long flightNumber, String date) {

        Long flightId = flightRepository
                .getFlightIdByFlightNumberAndDate(flightNumber, date)
                .orElseThrow(() -> new FlightException(
                        InvalidFlightEnum.FLIGHT_NOT_FOUND.getMessage(),
                        HttpStatus.NOT_FOUND
                ));

        // weight unit hardcoded -> value returned in kg
        double cargoWeight = loadService.calculateLoadWeight(
                loadService.getCargoList(flightId),
                WeightUnitEnum.KG
        );

        double baggageWeight = loadService.calculateLoadWeight(
                loadService.getBaggageList(flightId),
                WeightUnitEnum.KG
        );

        double totalWeight = loadService.calculateTotalLoadWeight(cargoWeight, baggageWeight);

        Map<String, Double> weightMap = Map.of(
                "cargoWeight", cargoWeight,
                "baggageWeight", baggageWeight,
                "totalWeight", totalWeight
        );

        return flightMapper.fromMapToDto(weightMap, FlightWeightDto.class);
    }

    @Transactional
    public FlightsWithBaggageDto getFlightsByIATAAirportCodeAndDate(String airportCode, String date) {

        List<Long> departingFlights = Collections.emptyList();
        if (DepartureAirportIATACodeEnum.containsValue(airportCode)) {
            departingFlights = flightRepository
                    .getNumberOfDepartingFlightsByAirportCodeAndDate(airportCode, date);
        }

        List<Long> arrivingFlights = Collections.emptyList();
        if (ArrivalAirportIATACodeEnum.containsValue(airportCode)) {
            arrivingFlights = flightRepository
                    .getNumberOfArrivingFlightsByAirportCodeAndDate(airportCode, date);
        }

        Integer totalNumberOfBaggageDeparting = loadService.getNumberOfBaggagePieces(departingFlights);
        Integer totalNumberOfBaggageArriving = loadService.getNumberOfBaggagePieces(arrivingFlights);

        Map<String, Integer> flightsMap = Map.of(
                "numberOfFlightsDeparting", departingFlights.size(),
                "numberOfFlightsArriving", arrivingFlights.size(),
                "totalNumberOfBaggageArriving", totalNumberOfBaggageArriving,
                "totalNumberOfBaggageDeparting", totalNumberOfBaggageDeparting
        );

        return flightMapper.fromMapToDto(flightsMap, FlightsWithBaggageDto.class);
    }

}
