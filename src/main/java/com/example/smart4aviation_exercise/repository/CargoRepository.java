package com.example.smart4aviation_exercise.repository;

import com.example.smart4aviation_exercise.entity.load.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query(value = "SELECT c " +
            "FROM Cargo c " +
            "WHERE c.cargo.flightId = :flightId")
    List<Cargo> getCargoByFlightId(@Param("flightId") Long flightId);

}
