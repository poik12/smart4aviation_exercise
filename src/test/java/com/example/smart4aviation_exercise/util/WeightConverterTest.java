package com.example.smart4aviation_exercise.util;

import com.example.smart4aviation_exercise.BeanTestData;
import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WeightConverterTest extends BeanTestData {

    @Autowired
    private WeightConverter underTest;

    @Test
    @DisplayName(value = "Should convert weight by weight unit and weight value")
    void should_convertWeight_byWeightUnitAndWeightValue() {
        // given
        WeightUnitEnum kg = WeightUnitEnum.KG;
        double lbValue = 100;
        double expectedKg = 100 * 0.453_592_37;

        WeightUnitEnum lb = WeightUnitEnum.LB;
        double kgValue = 100;
        double expectedLb = 100 / 0.453_592_37;

        // when
        var resultKg = underTest.convertWeight(lb, kgValue);
        var resultLb = underTest.convertWeight(kg, lbValue);

        // then
        assertAll(() -> {
            assertThat(resultKg).isEqualTo(expectedKg);
            assertThat(resultLb).isEqualTo(expectedLb);
        });
    }


    @Test
    @DisplayName(value = "Should round weight to two decimal places")
    void should_roundWeightToTwoDecimalPlaces() {
        // given
        double weightValue1 = 100.520954545;
        double weightValue2 = 100.524954545;
        double weightValue3 = 100.52594545;

        // when
        var result1 = underTest.roundWeightToTwoDecimalPlaces(weightValue1);
        var result2 = underTest.roundWeightToTwoDecimalPlaces(weightValue2);
        var result3 = underTest.roundWeightToTwoDecimalPlaces(weightValue3);

        // then
        assertAll(() -> {
            assertThat(result1).isEqualTo(100.52);
            assertThat(result2).isEqualTo(100.52);
            assertThat(result3).isEqualTo(100.53);
        });
    }

}