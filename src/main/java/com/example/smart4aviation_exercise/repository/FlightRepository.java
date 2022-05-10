package com.example.smart4aviation_exercise.repository;

import com.example.smart4aviation_exercise.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    @Query(value = "SELECT f.flight_id " +
            "FROM flights f " +
            "WHERE f.flight_number = :flightNumber " +
            "AND f.departure_date LIKE :departureDate%",
            nativeQuery = true)
    Optional<Long> getFlightIdByFlightNumberAndDate(@Param("flightNumber") Long flightNumber,
                                                    @Param("departureDate") String date);


    @Query(value = "SELECT f.flight_id " +
            "FROM flights f " +
            "WHERE f.departure_airport_iata_code = :airportCode " +
            "AND f.departure_date LIKE :flightDate%",
            nativeQuery = true)
    List<Long> getNumberOfDepartingFlightsByAirportCodeAndDate(@Param("airportCode") String airportCode,
                                                               @Param("flightDate") String date);

    @Query(value = "SELECT f.flight_id " +
            "FROM flights f " +
            "WHERE f.arrival_airport_iata_code = :airportCode " +
            "AND f.departure_date LIKE :flightDate%",
            nativeQuery = true)
    List<Long> getNumberOfArrivingFlightsByAirportCodeAndDate(@Param("airportCode") String airportCode,
                                                              @Param("flightDate") String date);

}
