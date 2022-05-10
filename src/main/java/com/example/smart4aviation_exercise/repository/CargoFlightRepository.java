package com.example.smart4aviation_exercise.repository;

import com.example.smart4aviation_exercise.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoFlightRepository extends JpaRepository<CargoEntity, Long> {
}
