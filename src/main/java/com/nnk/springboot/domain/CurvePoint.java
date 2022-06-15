package com.nnk.springboot.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @Digits(integer = 20, fraction = 0)
    @NotNull(message = "CurveId is mandatory")
    @Min(value = 0, message = "The value must be positive")
    @Column(name = "curve_id")
    private Integer curvePointId ;

    @Digits(integer = 20, fraction = 2)
    @Min(value = 0, message = "The value must be positive")
    @NotNull(message = "Numbers has to be present")
    @Column(name = "term")
    private Double term ;

    @Digits(integer = 20, fraction = 2)
    @Min(value = 0, message = "The value must be positive")
    @NotNull(message = "Numbers has to be present")
    @Column(name = "value")
    private Double value ;


    public CurvePoint(int curvePointId, double term, double value) {
    }
}
