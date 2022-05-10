package com.example.smart4aviation_exercise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightWeightDto {

    private double cargoWeight;
    private double baggageWeight;
    private double totalWeight;

}
