package com.example.smart4aviation_exercise;

import com.example.smart4aviation_exercise.dto.response.FlightWeightDto;
import com.example.smart4aviation_exercise.dto.response.FlightsWithBaggageDto;
import com.example.smart4aviation_exercise.entity.CargoEntity;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import com.example.smart4aviation_exercise.entity.enums.ArrivalAirportIATACodeEnum;
import com.example.smart4aviation_exercise.entity.enums.DepartureAirportIATACodeEnum;
import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import com.example.smart4aviation_exercise.entity.load.Baggage;
import com.example.smart4aviation_exercise.entity.load.Cargo;
import com.example.smart4aviation_exercise.repository.CargoFlightRepository;
import com.example.smart4aviation_exercise.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
public class BeanTestData {

    protected static final long FLIGHT_NUMBER_FIRST = 1000L;
    protected static final String DEPARTURE_DATE_STRING_FIRST = "2018-01-19";
    protected static final ArrivalAirportIATACodeEnum ARRIVAL_AIRPORT_CODE_FIRST = ArrivalAirportIATACodeEnum.MIT;
    protected static final DepartureAirportIATACodeEnum DEPARTURE_AIRPORT_CODE_FIRST = DepartureAirportIATACodeEnum.YYZ;
    protected static final double CARGO_WEIGHT_FIRST = 600.0;
    protected static final double BAGGAGE_WEIGHT_FIRST = 6000.0;
    protected static final double TOTAL_WEIGHT_FIRST = 6600.0;

    protected static final long FLIGHT_NUMBER_SECOND = 2000L;
    protected static final String DEPARTURE_DATE_STRING_SECOND = "2020-03-14";
    protected static final ArrivalAirportIATACodeEnum ARRIVAL_AIRPORT_CODE_SECOND = ArrivalAirportIATACodeEnum.LEW;
    protected static final DepartureAirportIATACodeEnum DEPARTURE_AIRPORT_CODE_SECOND = DepartureAirportIATACodeEnum.LAX;

    protected static final long FLIGHT_NUMBER_THIRD = 3000L;
    protected static final String DEPARTURE_DATE_STRING_THIRD = "2020-03-14";
    protected static final ArrivalAirportIATACodeEnum ARRIVAL_AIRPORT_CODE_THIRD = ArrivalAirportIATACodeEnum.PPX;
    protected static final DepartureAirportIATACodeEnum DEPARTURE_AIRPORT_CODE_THIRD = DepartureAirportIATACodeEnum.LAX;

    protected static final int FLIGHT_PIECES = 50;

    protected static final int FLIGHT_PIECES_TOTAL_DEPARTING = 300;
    protected static final int NUMBER_OF_FLIGHTS_DEPARTING = 2;

    protected static final int FLIGHT_PIECES_TOTAL_ARRIVING = 150;
    protected static final int NUMBER_OF_FLIGHTS_ARRIVING = 1;



    @Autowired
    protected FlightRepository flightRepository;
    @Autowired
    protected CargoFlightRepository cargoFlightRepository;

    protected FlightWeightDto initFlightWeightDtoForFlightFirst() {
        return FlightWeightDto.builder()
                .cargoWeight(CARGO_WEIGHT_FIRST)
                .baggageWeight(BAGGAGE_WEIGHT_FIRST)
                .totalWeight(TOTAL_WEIGHT_FIRST)
                .build();
    }

    protected FlightEntity initFlightEntityFirst() {
        FlightEntity flightEntity = FlightEntity.builder()
                .flightId(null)
                .flightNumber(FLIGHT_NUMBER_FIRST)
                .cargo(null)
                .arrivalAirportIATACode(ARRIVAL_AIRPORT_CODE_FIRST)
                .departureAirportIATACode(DEPARTURE_AIRPORT_CODE_FIRST)
                .departureDate(Date.from(
                        Instant.parse(DEPARTURE_DATE_STRING_FIRST + "T12:04:09-02:00")
                ))
                .build();

        return flightRepository.save(flightEntity);
    }

    protected FlightsWithBaggageDto initFlightsWithBaggageDtoForFlightFirst() {
        return FlightsWithBaggageDto.builder()
                .numberOfFlightsArriving(NUMBER_OF_FLIGHTS_ARRIVING)
                .numberOfFlightsDeparting(0)
                .totalNumberOfBaggageArriving(FLIGHT_PIECES_TOTAL_ARRIVING)
                .totalNumberOfBaggageDeparting(0)
                .build();
    }

    protected FlightEntity initFlightEntitySecond() {
        FlightEntity flightEntity = FlightEntity.builder()
                .flightId(null)
                .flightNumber(FLIGHT_NUMBER_SECOND)
                .cargo(null)
                .arrivalAirportIATACode(ARRIVAL_AIRPORT_CODE_SECOND)
                .departureAirportIATACode(DEPARTURE_AIRPORT_CODE_SECOND)
                .departureDate(Date.from(
                        Instant.parse(DEPARTURE_DATE_STRING_SECOND + "T11:04:09-02:00")
                ))
                .build();

        return flightRepository.save(flightEntity);
    }

    protected FlightsWithBaggageDto initFlightsWithBaggageDtoForFlightSecond() {
        return FlightsWithBaggageDto.builder()
                .numberOfFlightsArriving(0)
                .numberOfFlightsDeparting(NUMBER_OF_FLIGHTS_DEPARTING)
                .totalNumberOfBaggageArriving(0)
                .totalNumberOfBaggageDeparting(FLIGHT_PIECES_TOTAL_DEPARTING)
                .build();
    }

    protected FlightEntity initFlightEntityThird() {
        FlightEntity flightEntity = FlightEntity.builder()
                .flightId(null)
                .flightNumber(FLIGHT_NUMBER_THIRD)
                .cargo(null)
                .arrivalAirportIATACode(ARRIVAL_AIRPORT_CODE_THIRD)
                .departureAirportIATACode(DEPARTURE_AIRPORT_CODE_THIRD)
                .departureDate(Date.from(
                        Instant.parse(DEPARTURE_DATE_STRING_THIRD + "T15:05:03-02:00")
                ))
                .build();

        return flightRepository.save(flightEntity);
    }

    protected void initCargoEntity(FlightEntity flightEntity) {
        CargoEntity cargoEntity = new CargoEntity();

        cargoEntity.setFlight(flightEntity);
        cargoEntity.setBaggage(List.of(
                new Baggage(1000, WeightUnitEnum.KG, FLIGHT_PIECES, cargoEntity),
                new Baggage(2000, WeightUnitEnum.KG, FLIGHT_PIECES, cargoEntity),
                new Baggage(3000, WeightUnitEnum.KG, FLIGHT_PIECES, cargoEntity)
        ));
        cargoEntity.setCargo(List.of(
                new Cargo(100, WeightUnitEnum.KG, FLIGHT_PIECES, cargoEntity),
                new Cargo(200, WeightUnitEnum.KG, FLIGHT_PIECES, cargoEntity),
                new Cargo(300, WeightUnitEnum.KG, FLIGHT_PIECES, cargoEntity)
        ));

        cargoFlightRepository.save(cargoEntity);
    }
}

