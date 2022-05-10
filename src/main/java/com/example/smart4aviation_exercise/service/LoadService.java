package com.example.smart4aviation_exercise.service;

import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import com.example.smart4aviation_exercise.entity.load.Baggage;
import com.example.smart4aviation_exercise.entity.load.Cargo;
import com.example.smart4aviation_exercise.entity.load.Load;
import com.example.smart4aviation_exercise.repository.BaggageRepository;
import com.example.smart4aviation_exercise.repository.CargoRepository;
import com.example.smart4aviation_exercise.util.WeightConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoadService {

    private final BaggageRepository baggageRepository;
    private final CargoRepository cargoRepository;
    private final WeightConverter weightConverter;

    public List<Baggage> getBaggageList(Long flightId) {
        return baggageRepository.getBaggageByFlightId(flightId);
    }

    public List<Cargo> getCargoList(Long flightId) {
        return cargoRepository.getCargoByFlightId(flightId);
    }

    public double calculateLoadWeight(List<? extends Load> loadings, WeightUnitEnum targetWeightUnit) {
        Map<WeightUnitEnum, Integer> weightMap = getLoadingsWeightMap(loadings);
        return calculateWeightMap(weightMap, targetWeightUnit);
    }

    private Map<WeightUnitEnum, Integer> getLoadingsWeightMap(List<? extends Load> loadings) {
        Map<WeightUnitEnum, Integer> weightMap = new HashMap<>();

        for (WeightUnitEnum weightUnit : WeightUnitEnum.values()) {
            Integer weightValue = loadings.stream()
                    .filter(load -> load.getWeightUnit().equals(weightUnit))
                    .map(Load::getWeight)
                    .reduce(0, Integer::sum);
            weightMap.put(weightUnit, weightValue);
        }
        return weightMap;
    }

    private double calculateWeightMap(Map<WeightUnitEnum, Integer> weightMap, WeightUnitEnum totalWeightUnit) {
        double totalValue = 0;

        for (Map.Entry<WeightUnitEnum, Integer> entry : weightMap.entrySet()) {
            if (entry.getKey().equals(totalWeightUnit)) {
                totalValue += entry.getValue();
            } else {
                totalValue += weightConverter.convertWeight(entry.getKey(), entry.getValue());
            }
        }
        return weightConverter.roundWeightToTwoDecimalPlaces(totalValue);
    }

    public double calculateTotalLoadWeight(double... loadWeights) {
        double totalValue = Arrays.stream(loadWeights).sum();
        return weightConverter.roundWeightToTwoDecimalPlaces(totalValue);
    }

    public Integer getNumberOfBaggagePieces(List<Long> flightsIdList) {
        Integer totalNumberOfBaggagePieces = 0;
        if (flightsIdList.size() > 0) {
            totalNumberOfBaggagePieces = baggageRepository.getTotalNumberOfPieces(flightsIdList);
        }
        return totalNumberOfBaggagePieces;
    }

}
