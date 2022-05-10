package com.example.smart4aviation_exercise.data;

import com.example.smart4aviation_exercise.entity.CargoEntity;
import com.example.smart4aviation_exercise.entity.FlightEntity;
import com.example.smart4aviation_exercise.mapper.JsonMapper;
import com.example.smart4aviation_exercise.repository.CargoFlightRepository;
import com.example.smart4aviation_exercise.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Class for reading JSON files, converting and storing in a database
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class Data {

    private static final String FLIGHTS_JSON_PATH = "src/main/resources/json/flights.json";
    private static final String CARGOS_JSON_PATH = "src/main/resources/json/cargos.json";

    private final JsonMapper jsonMapper;
    private final FlightRepository flightRepository;
    private final CargoFlightRepository cargoFlightRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            saveFlightsInDb();
            saveCargosInDb();
            log.info("JSON files successfully converted and stored in the database");
        };
    }

    public void saveFlightsInDb() {
        List<FlightEntity> flights = jsonMapper.fromJsonFileToEntityList(FLIGHTS_JSON_PATH, FlightEntity[].class);
        flightRepository.saveAll(flights);
        log.info("JSON file [flights.json] has been stored in the database");
    }

    @Transactional
    public void saveCargosInDb() {
        List<CargoEntity> cargos = jsonMapper.fromJsonFileToEntityList(CARGOS_JSON_PATH, CargoEntity[].class);

        for (CargoEntity cargo : cargos) {

            Optional<FlightEntity> flightEntity = flightRepository.findById(cargo.getFlightId());

            if (flightEntity.isEmpty()) {
                log.warn("CargoEntity hasn't been stored in the database because flight with id " + cargo.getFlightId() +
                        " doesn't exist. CargoEntity: " + cargo);
                continue;
            }

            cargo.setFlight(flightEntity.get());
            cargo.getBaggage().forEach(b -> b.setCargo(cargo));
            cargo.getCargo().forEach(c -> c.setCargo(cargo));

            cargoFlightRepository.save(cargo);
        }
        log.info("JSON file [cargos.json] has been stored in database");
    }

}
