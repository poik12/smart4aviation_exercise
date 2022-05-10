package com.example.smart4aviation_exercise.mapper;

import com.example.smart4aviation_exercise.TestData;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import com.example.smart4aviation_exercise.exception.JsonException;
import com.example.smart4aviation_exercise.exception.message.InvalidJsonEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonMapperTest extends TestData {

    @InjectMocks
    private JsonMapper underTest;

    @Test
    @DisplayName(value = "Should map Array Json File to List of Entities")
    void should_mapArrayFromJsonFile_toListOfEntities() {
        // given
        String flightsJsonPath = "src/main/resources/json/flights.json";

        FlightEntity flightEntityFromJson = initFlightEntityFromJson();

        // when
        var result = underTest.fromJsonFileToEntityList(flightsJsonPath, FlightEntity[].class);

        // then
        assertAll(() -> {
            assertThat(result.size()).isEqualTo(5);
            assertThat(result.get(0)).isEqualTo(flightEntityFromJson);
            assertThat(result.get(0).getFlightId())
                    .isEqualTo(flightEntityFromJson.getFlightId());
            assertThat(result.get(0).getFlightNumber())
                    .isEqualTo(flightEntityFromJson.getFlightNumber());
            assertThat(result.get(0).getDepartureDate())
                    .isEqualTo(flightEntityFromJson.getDepartureDate());
            assertThat(result.get(0).getArrivalAirportIATACode())
                    .isEqualTo(flightEntityFromJson.getArrivalAirportIATACode());
            assertThat(result.get(0).getDepartureAirportIATACode())
                    .isEqualTo(flightEntityFromJson.getDepartureAirportIATACode());
        });
    }

    @Test
    @DisplayName(value = "Should throw JsonException by Fake Json file path")
    void should_throwJsonException_byFakeJsonFilePath() {
        // given
        String fakeJsonPath = "src/main/resources/json/FAKE.json";

        // when
        var result = assertThrows(
                JsonException.class,
                () -> underTest.fromJsonFileToEntityList(fakeJsonPath, FlightEntity[].class)
        );

        // then
        assertThat(result.getMessage())
                .isEqualTo(InvalidJsonEnum.FAILED_READING_FILE.getMessage() + fakeJsonPath);
    }
}