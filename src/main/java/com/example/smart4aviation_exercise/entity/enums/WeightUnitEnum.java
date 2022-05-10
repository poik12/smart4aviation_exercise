package com.example.smart4aviation_exercise.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WeightUnitEnum {

    @JsonProperty(value = "kg")
    KG {
        @Override
        public double convertWeightBetweenKilogramsAndPounds(double weight) {
            return  weight / 0.453_592_37;
        }
    },
    @JsonProperty(value = "lb")
    LB {
        @Override
        public double convertWeightBetweenKilogramsAndPounds(double weight) {
            return weight * 0.453_592_37;
        }
    };

    public abstract double convertWeightBetweenKilogramsAndPounds(double weight);

}
