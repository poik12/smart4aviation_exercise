package com.example.smart4aviation_exercise;

import com.example.smart4aviation_exercise.dto.response.FlightWeightDto;
import com.example.smart4aviation_exercise.entity.CargoEntity;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import com.example.smart4aviation_exercise.entity.enums.ArrivalAirportIATACodeEnum;
import com.example.smart4aviation_exercise.entity.enums.DepartureAirportIATACodeEnum;
import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import com.example.smart4aviation_exercise.entity.load.Baggage;
import com.example.smart4aviation_exercise.entity.load.Cargo;
import com.example.smart4aviation_exercise.repository.BaggageRepository;
import com.example.smart4aviation_exercise.repository.CargoFlightRepository;
import com.example.smart4aviation_exercise.repository.FlightRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ExtendWith(value = MockitoExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestData {

    protected static final long FLIGHT_ID_FIRST = 0L;
    protected static final long FLIGHT_NUMBER_FIRST = 1000L;
    protected static final int FLIGHT_PIECES_FIRST = 50;
    protected static final int FLIGHT_PIECES_FIRST_TOTAL = 150;
    protected static final String DEPARTURE_DATE_STRING_FIRST = "2018-01-19";
    protected static final ArrivalAirportIATACodeEnum ARRIVAL_AIRPORT_CODE_FIRST = ArrivalAirportIATACodeEnum.GDN;
    protected static final DepartureAirportIATACodeEnum DEPARTURE_AIRPORT_CODE_FIRST = DepartureAirportIATACodeEnum.YYZ;

    protected static final long FLIGHT_ID_SECOND = 1L;
    protected static final long FLIGHT_NUMBER_SECOND = 2000L;
    protected static final int FLIGHT_PIECES_SECOND = 60;
    protected static final String DEPARTURE_DATE_STRING_SECOND = "2020-03-14";
    protected static final ArrivalAirportIATACodeEnum ARRIVAL_AIRPORT_CODE_SECOND = ArrivalAirportIATACodeEnum.LEW;
    protected static final DepartureAirportIATACodeEnum DEPARTURE_AIRPORT_CODE_SECOND = DepartureAirportIATACodeEnum.YYT;

    @Autowired
    protected FlightRepository flightRepository;
    @Autowired
    protected CargoFlightRepository cargoFlightRepository;

    protected FlightEntity initFlightEntityFirst() {

        Date date = Date.from(Instant.parse(DEPARTURE_DATE_STRING_FIRST + "T12:04:09-02:00"));

        FlightEntity flightEntity = FlightEntity.builder()
                .flightId(FLIGHT_ID_FIRST)
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

    protected FlightEntity initFlightEntitySecond() {

        FlightEntity flightEntity = FlightEntity.builder()
                .flightId(FLIGHT_ID_SECOND)
                .flightNumber(FLIGHT_NUMBER_SECOND)
                .cargo(null)
                .arrivalAirportIATACode(ARRIVAL_AIRPORT_CODE_SECOND)
                .departureAirportIATACode(DEPARTURE_AIRPORT_CODE_SECOND)
                .departureDate(Date.from(
                        Instant.parse(DEPARTURE_DATE_STRING_SECOND + "T12:04:09-02:00")
                ))
                .build();

        return flightRepository.save(flightEntity);
    }

    protected CargoEntity initCargoEntity(FlightEntity flightEntity) {

        CargoEntity cargoEntity = new CargoEntity();

        cargoEntity.setFlight(flightEntity);
        cargoEntity.setBaggage(List.of(
                new Baggage(100, WeightUnitEnum.KG, FLIGHT_PIECES_FIRST, cargoEntity),
                new Baggage(200, WeightUnitEnum.KG, FLIGHT_PIECES_FIRST, cargoEntity),
                new Baggage(300, WeightUnitEnum.LB, FLIGHT_PIECES_FIRST, cargoEntity)
        ));
        cargoEntity.setCargo(List.of(
                new Cargo(100, WeightUnitEnum.KG, FLIGHT_PIECES_FIRST, cargoEntity),
                new Cargo(200, WeightUnitEnum.KG, FLIGHT_PIECES_FIRST, cargoEntity),
                new Cargo(300, WeightUnitEnum.LB, FLIGHT_PIECES_FIRST, cargoEntity)
        ));

        return cargoFlightRepository.save(cargoEntity);
    }

    protected FlightWeightDto initFlightWeightDto() {
        return FlightWeightDto.builder()
                .cargoWeight(100.00)
                .baggageWeight(200.00)
                .totalWeight(300.00)
                .build();
    }

    protected FlightEntity initFlightEntityFromJson() {
        return FlightEntity.builder()
                .flightId(0L)
                .flightNumber(7216L)
                .departureAirportIATACode(DepartureAirportIATACodeEnum.YYT)
                .arrivalAirportIATACode(ArrivalAirportIATACodeEnum.PPX)
                .departureDate(Date.from(Instant.parse("2019-10-10T12:04:09-02:00")))
                .build();
    }
}
