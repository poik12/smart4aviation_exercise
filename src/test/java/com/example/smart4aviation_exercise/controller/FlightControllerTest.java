package com.example.smart4aviation_exercise.controller;

import com.example.smart4aviation_exercise.BeanTestData;
import com.example.smart4aviation_exercise.controller.handler.FlightControllerAdvice;
import com.example.smart4aviation_exercise.dto.response.FlightWeightDto;
import com.example.smart4aviation_exercise.dto.response.FlightsWithBaggageDto;
import com.example.smart4aviation_exercise.exception.FlightException;
import com.example.smart4aviation_exercise.exception.message.InvalidFlightEnum;
import com.example.smart4aviation_exercise.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FlightControllerTest extends BeanTestData {

    public MockMvc mockMvc;

    @Autowired
    public FlightController flightController;

    @MockBean
    public FlightService flightService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(flightController)
                .setControllerAdvice(new FlightControllerAdvice())
                .build();
    }

    @Test
    @DisplayName(value = "Should get FlightWeightDto by Flight Number and Date")
    void should_getFlightWeightDto_byFlightNumberAndDate() throws Exception {
        // given
        FlightWeightDto flightWeightDto = initFlightWeightDtoForFlightFirst();

        // when // then
        when(flightService.getFlightWeightByFlightNumberAndDate(any(), any()))
                .thenReturn(flightWeightDto);

        mockMvc.perform(
                get("/flights/weights")
                        .param("flightNumber", "1000")
                        .param("date", "2018-01-19")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cargoWeight")
                        .value(flightWeightDto.getCargoWeight()))
                .andExpect(jsonPath("$.baggageWeight")
                        .value(flightWeightDto.getBaggageWeight()))
                .andExpect(jsonPath("$.totalWeight")
                        .value(flightWeightDto.getTotalWeight()));
    }

    @Test
    @DisplayName(value = "Should get FlightsWithBaggageDto by Airport Code and Date")
    void should_getFlightsWithBaggageDto_byAirportCodeAndDate() throws Exception {
        // given
        FlightsWithBaggageDto flightsWithBaggageDto = initFlightsWithBaggageDtoForFlightFirst();

        // when // then
        when(flightService.getFlightsByIATAAirportCodeAndDate(any(), any()))
                .thenReturn(flightsWithBaggageDto);

        mockMvc.perform(
                        get("/flights/airports")
                                .param("airportCode", "KRK")
                                .param("date", "2018-01-19")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfFlightsArriving")
                        .value(flightsWithBaggageDto.getNumberOfFlightsArriving()))
                .andExpect(jsonPath("$.numberOfFlightsDeparting")
                        .value(flightsWithBaggageDto.getNumberOfFlightsDeparting()))
                .andExpect(jsonPath("$.totalNumberOfBaggageArriving")
                        .value(flightsWithBaggageDto.getTotalNumberOfBaggageArriving()))
                .andExpect(jsonPath("$.totalNumberOfBaggageDeparting")
                        .value(flightsWithBaggageDto.getTotalNumberOfBaggageDeparting()));
    }

    @Test
    @DisplayName(value = "Should handle exception by Fake Flight Number and Date")
    void should_handleException_byFakeFlightNumberAndDate() throws Exception {
        // given
        given(flightService.getFlightWeightByFlightNumberAndDate(any(), any()))
                .willThrow(new FlightException(
                        InvalidFlightEnum.FLIGHT_NOT_FOUND.getMessage(),
                        HttpStatus.NOT_FOUND
                ));

        // when // then
        mockMvc.perform(
                        get("/flights/weights")
                                .param("flightNumber", "1000")
                                .param("date", "2018-01-19")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                ).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status")
                        .value(HttpStatus.NOT_FOUND.toString().substring(4)))
                .andExpect(jsonPath("$.message")
                        .value(InvalidFlightEnum.FLIGHT_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$.timestamp")
                        .exists());
    }

}