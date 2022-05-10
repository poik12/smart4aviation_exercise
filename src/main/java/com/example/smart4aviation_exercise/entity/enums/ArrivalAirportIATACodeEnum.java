package com.example.smart4aviation_exercise.entity.enums;

public enum ArrivalAirportIATACodeEnum {

    MIT,
    LEW,
    GDN,
    KRK,
    PPX;

    public static boolean containsValue(String airportCode) {
        for (ArrivalAirportIATACodeEnum value : ArrivalAirportIATACodeEnum.values()) {
            if (value.name().equalsIgnoreCase(airportCode)) return true;
        }
        return false;
    }
}
