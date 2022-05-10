package com.example.smart4aviation_exercise.entity;

import com.example.smart4aviation_exercise.entity.enums.ArrivalAirportIATACodeEnum;
import com.example.smart4aviation_exercise.entity.enums.DepartureAirportIATACodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightEntity implements Serializable {

    @Id
    @SequenceGenerator(
            name = "flights_sequence",
            sequenceName = "flights_sequence",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flights_sequence"
    )
    @Column(name = "flight_id")
    private Long flightId;

    private Long flightNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "departure_airport_iata_code")
    private DepartureAirportIATACodeEnum departureAirportIATACode;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "arrival_airport_iata_code")
    private ArrivalAirportIATACodeEnum arrivalAirportIATACode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @OneToOne(
            mappedBy = "flight",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private CargoEntity cargo;

    public void setCargo(CargoEntity cargoEntity) {
        this.cargo = cargoEntity;
        cargoEntity.setFlight(this);
    }

}
