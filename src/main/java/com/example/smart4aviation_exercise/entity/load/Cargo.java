package com.example.smart4aviation_exercise.entity.load;

import com.example.smart4aviation_exercise.entity.CargoEntity;
import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cargos")
@Data
@NoArgsConstructor
public class Cargo extends Load implements Serializable {

    @Id
    @SequenceGenerator(
            name = "cargos_sequence",
            sequenceName = "cargos_sequence",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cargos_sequence"
    )
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private CargoEntity cargo;

    public Cargo(int weight, WeightUnitEnum weightUnit, int pieces, CargoEntity cargo) {
        super(weight, weightUnit, pieces);
        this.cargo = cargo;
    }

}
