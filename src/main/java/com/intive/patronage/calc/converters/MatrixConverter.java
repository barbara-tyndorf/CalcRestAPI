package com.intive.patronage.calc.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
@Converter
public class MatrixConverter implements AttributeConverter<BigDecimal[][], String> {

    private final String SEPARATOR = "-";

    private AttributeConverter<BigDecimal[], String> vectorConverter;

    public MatrixConverter(AttributeConverter<BigDecimal[], String> vectorConverter) {
        this.vectorConverter = vectorConverter;
    }

    @Override
    public String convertToDatabaseColumn(BigDecimal[][] matrix) {

        return matrix == null ? null : Arrays.stream(matrix)
                .map(m -> vectorConverter.convertToDatabaseColumn(m)).collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public BigDecimal[][] convertToEntityAttribute(String value) {
        if (value != null) {
            String[] rows = value.split(SEPARATOR);
            return Arrays.stream(rows).map(v -> vectorConverter.convertToEntityAttribute(v))
                    .toArray(BigDecimal[][]::new);
        } else {
            return null;
        }
    }
}
