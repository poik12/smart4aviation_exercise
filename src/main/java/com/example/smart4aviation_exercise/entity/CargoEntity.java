package com.example.smart4aviation_exercise.entity;

import com.example.smart4aviation_exercise.entity.load.Baggage;
import com.example.smart4aviation_exercise.entity.load.Cargo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flight_cargos")
@Data
@NoArgsConstructor
public class CargoEntity implements Serializable {

    @Id
    @Column(name = "flight_id")
    private Long flightId;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private FlightEntity flight;

    @OneToMany(
            mappedBy = "cargo",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Baggage> baggage = new ArrayList<>();

    @OneToMany(
            mappedBy = "cargo",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Cargo> cargo = new ArrayList<>();

    public void addBaggage(Baggage baggage) {
       this.baggage.add(baggage);
       baggage.setCargo(this);
    }

    public void removeBaggage(Baggage baggage) {
        baggage.setCargo(null);
        this.baggage.remove(baggage);
    }

    public void addCargo(Cargo cargo) {
        this.cargo.add(cargo);
        cargo.setCargo(this);
    }

    public void removeCargo(Cargo cargo) {
        cargo.setCargo(null);
        this.cargo.remove(cargo);
    }

}
