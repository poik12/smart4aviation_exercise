package com.example.smart4aviation_exercise.repository;

import com.example.smart4aviation_exercise.TestData;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FlightRepositoryTest extends TestData {

    @Autowired
    private FlightRepository underTest;

    @Test
    @DisplayName(value = "Should get Present Optional<FlightId> by Flight Number and Departure Date")
    void should_getFlightId_byFlightNumberAndDate() {
        // given
        FlightEntity flightEntity = initFlightEntityFirst();

        // when
        var result = underTest.getFlightIdByFlightNumberAndDate(
                flightEntity.getFlightNumber(),
                DEPARTURE_DATE_STRING_FIRST
        );

        // then
        assertAll(() -> {
            assertThat(result).isNotEmpty();
            assertThat(result.get()).isEqualTo(flightEntity.getFlightId());
        });
    }

    @Test
    @DisplayName(value = "Should get Empty Optional<FlightId> by Flight Number and FAKE Departure Date")
    void should_notGetFlightId_byFlightNumberAndFakeDate() {
        // given
        FlightEntity flightEntity = initFlightEntityFirst();
        String fakeDepartureStringDate = "1950-09-12";

        // when
        var result = underTest.getFlightIdByFlightNumberAndDate(
                flightEntity.getFlightNumber(),
                fakeDepartureStringDate
        );

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName(value = "Should get Empty Optional<FlightId> by FAKE Flight Number and Departure Date")
    void should_notGetFlightId_byFakeFlightNumberAndDate() {
        // given
        FlightEntity flightEntity = initFlightEntityFirst();
        long fakeFlightNumber = flightEntity.getFlightNumber() + 5L;

        // when
        var result = underTest.getFlightIdByFlightNumberAndDate(
                fakeFlightNumber,
                DEPARTURE_DATE_STRING_FIRST
        );

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName(value = "Should get Number of departing flights by Airport Code and Departure Date")
    void should_getNumberOfDepartingFlights_byAirportCodeAndDate() {
        // given
        FlightEntity flightEntityFirst = initFlightEntityFirst();
        FlightEntity flightEntitySecond = initFlightEntitySecond();

        // when
        var result = underTest.getNumberOfDepartingFlightsByAirportCodeAndDate(
                flightEntityFirst.getDepartureAirportIATACode().toString(),
                DEPARTURE_DATE_STRING_FIRST
        );

        // then
        assertAll(() -> {
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.get(0)).isEqualTo(flightEntityFirst.getFlightId());
        });
    }

    @Test
    @DisplayName(value = "Should get Number of arriving flights by Airport Code and Departure Date")
    void should_getNumberOfArrivingFlights_byAirportCodeAndDate() {
        // given
        FlightEntity flightEntityFirst = initFlightEntityFirst();
        FlightEntity flightEntitySecond = initFlightEntitySecond();

        // when
        var result = underTest.getNumberOfArrivingFlightsByAirportCodeAndDate(
                flightEntityFirst.getArrivalAirportIATACode().toString(),
                DEPARTURE_DATE_STRING_FIRST
        );

        // then
        assertAll(() -> {
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.get(0)).isEqualTo(flightEntityFirst.getFlightId());
        });
    }

}