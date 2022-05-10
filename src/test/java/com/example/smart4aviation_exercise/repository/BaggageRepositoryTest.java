package com.example.smart4aviation_exercise.repository;

import com.example.smart4aviation_exercise.TestData;
import com.example.smart4aviation_exercise.entity.CargoEntity;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BaggageRepositoryTest extends TestData {

    @Autowired
    private BaggageRepository underTest;

    @Test
    @DisplayName(value = "Should get List<Baggage> by Flight Id")
    void should_getBaggageList_byFlightId() {
        // given
        FlightEntity flightEntity = initFlightEntityFirst();
        CargoEntity cargoEntity = initCargoEntity(flightEntity);

        // when
        var result = underTest.getBaggageByFlightId(flightEntity.getFlightId());

        // then
        assertAll(() -> {
            assertThat(result.size()).isEqualTo(cargoEntity.getBaggage().size());
            assertThat(result.get(0)).isEqualTo(cargoEntity.getBaggage().get(0));
            assertThat(result.get(1)).isEqualTo(cargoEntity.getBaggage().get(1));
            assertThat(result.get(2)).isEqualTo(cargoEntity.getBaggage().get(2));
        });
    }

    @Test
    @DisplayName(value = "Should get total number of Pieces by List<FlightId>")
    void should_getSumOfPieces_byListOfFlightId() {
        // given
        initCargoEntity(initFlightEntityFirst());
        initCargoEntity(initFlightEntitySecond());

        List<Long> flightIdList = List.of(FLIGHT_ID_FIRST);

        // when
        var result = underTest.getTotalNumberOfPieces(flightIdList);

        // then
        assertAll(() -> {
            assertThat(result).isEqualTo(FLIGHT_PIECES_FIRST_TOTAL);
        });
    }

}