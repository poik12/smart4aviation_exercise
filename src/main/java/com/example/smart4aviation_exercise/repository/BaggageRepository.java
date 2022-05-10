package com.example.smart4aviation_exercise.repository;

import com.example.smart4aviation_exercise.entity.load.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {

    @Query(value = "SELECT b " +
            "FROM Baggage b " +
            "WHERE b.cargo.flightId = :flightId")
    List<Baggage> getBaggageByFlightId(Long flightId);

    @Query(value="SELECT SUM(b.pieces) " +
            "FROM Baggage b " +
            "WHERE b.cargo.flightId IN :flights")
    Integer getTotalNumberOfPieces(@Param("flights") List<Long> flights);

}
