package com.example.smart4aviation_exercise.controller.handler;

import com.example.smart4aviation_exercise.controller.FlightController;
import com.example.smart4aviation_exercise.dto.exception.ExceptionMessageDto;
import com.example.smart4aviation_exercise.exception.FlightException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice(assignableTypes = FlightController.class)
public class FlightControllerAdvice {

    @ExceptionHandler(value = FlightException.class)
    public ResponseEntity<ExceptionMessageDto> handleException(FlightException flightException) {
        ExceptionMessageDto messageDto = ExceptionMessageDto.builder()
                .message(flightException.getMessage())
                .status(flightException.getHttpStatus())
                .timestamp(ZonedDateTime.now())
                .build();
        return ResponseEntity
                .status(flightException.getHttpStatus())
                .body(messageDto);
    }

}
