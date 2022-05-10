package com.example.smart4aviation_exercise.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class FlightMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T fromMapToDto(Map<?, ?> mapOfValues, Class<T> dtoClass) {
        T dto = mapper.convertValue(mapOfValues, dtoClass);
        log.info("Map: " + mapOfValues + " has been converted into: " + dto);
        return dto;
    }

}
