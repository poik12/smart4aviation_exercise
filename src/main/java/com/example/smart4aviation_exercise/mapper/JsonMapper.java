package com.example.smart4aviation_exercise.mapper;

import com.example.smart4aviation_exercise.exception.JsonException;
import com.example.smart4aviation_exercise.exception.message.InvalidJsonEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
@Slf4j
public class JsonMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> List<T> fromJsonFileToEntityList(String filePath, Class<T[]> arrayClassType) {
        File jsonFile = Paths.get(filePath).toFile();
        List<T> objectList;

        try {
            objectList = List.of(mapper.readValue(jsonFile, arrayClassType));
        } catch (IOException e) {
          throw new JsonException(InvalidJsonEnum.FAILED_READING_FILE.getMessage() + filePath);
        }
        log.info("File from path: [" + filePath + "] has been converted into an object list: " + objectList);
        return objectList;
    }

}
