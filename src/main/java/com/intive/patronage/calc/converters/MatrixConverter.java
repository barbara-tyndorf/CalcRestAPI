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

    private final String SEPARATOR = "|";

    private AttributeConverter<BigDecimal[], String> vectorConverter;

    public MatrixConverter(AttributeConverter<BigDecimal[], String> vectorConverter) {
        this.vectorConverter = vectorConverter;
    }

    @Override
    public String convertToDatabaseColumn(BigDecimal[][] matrix) {

        return matrix == null ? null : Arrays.asList(matrix).stream().map(m -> vectorConverter.convertToDatabaseColumn(m)).collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public BigDecimal[][] convertToEntityAttribute(String s) {
        return s == null ? null : Arrays.asList(s.split(SEPARATOR)).stream().map(v -> vectorConverter.convertToEntityAttribute(v)).toArray(BigDecimal[][]::new);
    }
}
