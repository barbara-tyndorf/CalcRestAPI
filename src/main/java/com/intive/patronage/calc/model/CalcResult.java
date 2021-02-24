package com.intive.patronage.calc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intive.patronage.calc.converters.MatrixConverter;
import com.intive.patronage.calc.converters.VectorConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CalcResult {

    private BigDecimal digit;

    @Convert(converter = VectorConverter.class)
    private BigDecimal[] vector;

    @Convert(converter = MatrixConverter.class)
    private BigDecimal[][] matrix;
}
