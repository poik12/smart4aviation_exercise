package com.example.smart4aviation_exercise.mapper;

import com.example.smart4aviation_exercise.TestData;
import com.example.smart4aviation_exercise.dto.response.FlightWeightDto;
import com.example.smart4aviation_exercise.dto.response.FlightsWithBaggageDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FlightMapperTest extends TestData {

    @InjectMocks
    private FlightMapper underTest;

    @Test
    @DisplayName(value = "Should map to FlightWeightsDto from Map of flights weights")
    void should_mapToFlightWeightDto_fromMapOfFlightWeights() {
        // given
        Map<String, Double> weightMap = Map.of(
                "cargoWeight", 100.00,
                "baggageWeight", 200.00,
                "totalWeight", 300.00
        );

        // when
        var result = underTest.fromMapToDto(weightMap, FlightWeightDto.class);

        // then
        assertAll(() ->{
            assertThat(result.getCargoWeight()).isEqualTo(100.00);
            assertThat(result.getBaggageWeight()).isEqualTo(200.00);
            assertThat(result.getTotalWeight()).isEqualTo(300.00);
        });
    }

    @Test
    @DisplayName(value = "Should map to FlightsWithBaggageDto from Map of flights")
    void should_mapToFlightsWithBaggageDto_fromMapOfFlights() {
        // given
        Map<String, Integer> flights = Map.of(
                "numberOfFlightsDeparting", 10,
                "numberOfFlightsArriving", 20,
                "totalNumberOfBaggageArriving",1000,
                "totalNumberOfBaggageDeparting",2000
        );

        // when
        var result = underTest.fromMapToDto(flights, FlightsWithBaggageDto.class);

        // then
        assertAll(() ->{
            assertThat(result.getNumberOfFlightsDeparting()).isEqualTo(10);
            assertThat(result.getNumberOfFlightsArriving()).isEqualTo(20);
            assertThat(result.getTotalNumberOfBaggageArriving()).isEqualTo(1000);
            assertThat(result.getTotalNumberOfBaggageDeparting()).isEqualTo(2000);
        });
    }

}