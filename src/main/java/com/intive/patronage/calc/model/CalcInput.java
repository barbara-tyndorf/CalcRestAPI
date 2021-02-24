package com.intive.patronage.calc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intive.patronage.calc.converters.MatrixConverter;
import com.intive.patronage.calc.converters.VectorConverter;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CalcInput {

    private BigDecimal digitA;

    @Convert(converter = VectorConverter.class)
    private BigDecimal[] vectorA;

    @Convert(converter = MatrixConverter.class)
    private BigDecimal[][] matrixA;

    private BigDecimal digitB;

    @Convert(converter = VectorConverter.class)
    private BigDecimal[] vectorB;

    @Convert(converter = MatrixConverter.class)
    private BigDecimal[][] matrixB;

    private CalcOperationType operation;
}