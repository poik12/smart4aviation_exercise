package com.example.smart4aviation_exercise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightsWithBaggageDto {

    private int numberOfFlightsArriving;
    private int numberOfFlightsDeparting;
    private int totalNumberOfBaggageArriving;
    private int totalNumberOfBaggageDeparting;

}



