package com.example.smart4aviation_exercise.entity.enums;

public enum DepartureAirportIATACodeEnum {

    SEA,
    YYZ,
    YYT,
    ANC,
    LAX;

    public static boolean containsValue(String airportCode) {
        for (DepartureAirportIATACodeEnum value : DepartureAirportIATACodeEnum.values()) {
            if (value.name().equalsIgnoreCase(airportCode)) return true;
        }
        return false;
    }

}
