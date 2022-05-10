package com.example.smart4aviation_exercise.repository;

import com.example.smart4aviation_exercise.TestData;
import com.example.smart4aviation_exercise.entity.CargoEntity;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CargoRepositoryTest extends TestData {

    @Autowired
    private CargoRepository underTest;

    @Test
    @DisplayName(value = "Should get List<Cargo> by Flight Id")
    void should_getCargo_byFlightId() {
        // given
        FlightEntity flightEntity = initFlightEntityFirst();
        CargoEntity cargoEntity = initCargoEntity(flightEntity);

        // when
        var result = underTest.getCargoByFlightId(flightEntity.getFlightId());

        // then
        assertAll(() -> {
            assertThat(result.size()).isEqualTo(cargoEntity.getCargo().size());
            assertThat(result.get(0)).isEqualTo(cargoEntity.getCargo().get(0));
            assertThat(result.get(1)).isEqualTo(cargoEntity.getCargo().get(1));
            assertThat(result.get(2)).isEqualTo(cargoEntity.getCargo().get(2));
        });
    }
}