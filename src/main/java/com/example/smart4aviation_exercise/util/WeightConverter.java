package com.example.smart4aviation_exercise.util;

import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import org.springframework.stereotype.Component;

@Component
public class WeightConverter {

    public double convertWeight(WeightUnitEnum weightUnit, double weightValue) {
        return weightUnit.convertWeightBetweenKilogramsAndPounds(weightValue);
    }

    public double roundWeightToTwoDecimalPlaces(double weightValue) {
        return Math.round(weightValue * 100.0) / 100.0;
    }

}
