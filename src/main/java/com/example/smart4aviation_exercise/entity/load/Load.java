package com.example.smart4aviation_exercise.entity.load;

import com.example.smart4aviation_exercise.entity.enums.WeightUnitEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Load implements Serializable {

    protected int weight;

    @Enumerated(value = EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    protected WeightUnitEnum weightUnit;

    protected int pieces;

}
