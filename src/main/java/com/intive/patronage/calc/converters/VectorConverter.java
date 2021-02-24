package com.intive.patronage.calc.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
@Converter
public class VectorConverter implements AttributeConverter<BigDecimal[], String> {

    private final String SEPARATOR = ";";

    @Override
    public String convertToDatabaseColumn(BigDecimal[] vector) {
        return vector == null ? null : Arrays.asList(vector).stream().map(n -> n.toString()).collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public BigDecimal[] convertToEntityAttribute(String value) {
        if (value != null) {
            String[] numbers = value.split(SEPARATOR);
            return Arrays.asList(numbers).stream().map(BigDecimal::new).toArray(BigDecimal[]::new);
        } else {
            return null;
        }
    }
}
