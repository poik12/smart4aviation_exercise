package com.example.smart4aviation_exercise.service;

import com.example.smart4aviation_exercise.BeanTestData;
import com.example.smart4aviation_exercise.dto.response.FlightWeightDto;
import com.example.smart4aviation_exercise.dto.response.FlightsWithBaggageDto;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import com.example.smart4aviation_exercise.exception.FlightException;
import com.example.smart4aviation_exercise.exception.message.InvalidFlightEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest extends BeanTestData {

    @Autowired
    private FlightService underTest;

    @BeforeEach
    void setUp() {
        FlightEntity flightEntityFirst = initFlightEntityFirst();
        initCargoEntity(flightEntityFirst);
        FlightEntity flightEntitySecond = initFlightEntitySecond();
        initCargoEntity(flightEntitySecond);
        FlightEntity flightEntityThird = initFlightEntityThird();
        initCargoEntity(flightEntityThird);
    }

    @Test
    @DisplayName(value = "Should get FlightWeightDto by Flight Number and Date")
    void should_getFlightWeightDto_byFlightNumberAndDate() {
        // given
        FlightWeightDto flightWeightDto = initFlightWeightDtoForFlightFirst();

        // when
        var result = underTest.getFlightWeightByFlightNumberAndDate(
                FLIGHT_NUMBER_FIRST,
                DEPARTURE_DATE_STRING_FIRST
        );

        // then
        assertThat(result).isEqualTo(flightWeightDto);
    }

    @Test
    @DisplayName(value = "Should throw FlightException by Fake Flight Number and Date")
    void should_getThrowFlightException_byFakeFlightNumberAndDate() {
        // given
        Long fakeFlightNumber = 999L;

        // when
        var result = assertThrows(
                FlightException.class,
                () -> underTest.getFlightWeightByFlightNumberAndDate(
                        fakeFlightNumber,
                        DEPARTURE_DATE_STRING_FIRST
                ));

        // then
        assertAll(() -> {
            assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(result.getMessage()).isEqualTo(InvalidFlightEnum.FLIGHT_NOT_FOUND.getMessage());
        });
    }

    @Test
    @DisplayName(value = "Should get FlightsWithBaggageDto by Arrival Airport Code and Date")
    void should_getFlightsWithBaggageDto_byArrivalAirportCodeAndDate() {
        // given
        FlightsWithBaggageDto flightsWithBaggageDto = initFlightsWithBaggageDtoForFlightFirst();

        // when
        var result = underTest.getFlightsByIATAAirportCodeAndDate(
                ARRIVAL_AIRPORT_CODE_FIRST.toString(),
                DEPARTURE_DATE_STRING_FIRST
        );
        System.out.println(result);

        // then
        assertThat(result).isEqualTo(flightsWithBaggageDto);
    }

    @Test
    @DisplayName(value = "Should get FlightsWithBaggageDto by Departure Airport Code and Date")
    void should_getFlightsWithBaggageDto_byDepartureAirportCodeAndDate() {
        // given
        FlightsWithBaggageDto flightsWithBaggageDto = initFlightsWithBaggageDtoForFlightSecond();

        // when
        var result = underTest.getFlightsByIATAAirportCodeAndDate(
                DEPARTURE_AIRPORT_CODE_SECOND.toString(),
                DEPARTURE_DATE_STRING_SECOND
        );
        System.out.println(result);

        // then
        assertThat(result).isEqualTo(flightsWithBaggageDto);
    }

}