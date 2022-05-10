package com.example.smart4aviation_exercise.exception.message;

public enum InvalidJsonEnum {

    FAILED_READING_FILE("Something went wrong while reading JSON from file: ");

    private final String message;

    InvalidJsonEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
