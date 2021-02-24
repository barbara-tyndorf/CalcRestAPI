package com.intive.patronage.calc.services;

import com.intive.patronage.calc.model.CalcInput;

import com.intive.patronage.calc.model.CalcOperationType;
import com.intive.patronage.calc.model.CalcResult;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CalcServiceTest {

    CalcService calcService = new CalcService();

    @Test
    void should_create_new_digit_result() {
        CalcInput calcInput = new CalcInput();
        // given
        calcInput.setDigitA(new BigDecimal(2));
        calcInput.setDigitB(new BigDecimal(2));
        calcInput.setOperation(CalcOperationType.ADD);

        // when
        CalcResult result = calcService.calc(calcInput);

        // then
        assertThat(result.getDigit()).isEqualTo(new BigDecimal(4));
        assertThat(result.getMatrix()).isNull();
        assertThat(result.getVector()).isNull();
    }

    @Test
    void should_create_new_matrix_result() {
        CalcInput calcInput = new CalcInput();
        // given
        calcInput.setDigitA(new BigDecimal(2));
        calcInput.setMatrixB(new BigDecimal[][]{
                {new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)},
                {new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)}
        });
        calcInput.setOperation(CalcOperationType.MUL);

        // when
        CalcResult result = calcService.calc(calcInput);

        // then
        assertThat(result.getDigit()).isNull();
        assertThat(result.getVector()).isNull();
        assertThat(result.getMatrix()).isEqualTo(new BigDecimal[][]{
                {new BigDecimal(2), new BigDecimal(4), new BigDecimal(6)},
                {new BigDecimal(2), new BigDecimal(4), new BigDecimal(6)}
        });
    }

    @Test
    void should_create_new_vector_result() {
        CalcInput calcInput = new CalcInput();
        // given
        calcInput.setMatrixA(new BigDecimal[][]{
                {new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)},
                {new BigDecimal(3), new BigDecimal(2), new BigDecimal(1)}
        });
        calcInput.setVectorB(new BigDecimal[]{new BigDecimal(2), new BigDecimal(3), new BigDecimal(4)});
        calcInput.setOperation(CalcOperationType.MUL);

        // when
        CalcResult result = calcService.calc(calcInput);

        // then
        assertThat(result.getDigit()).isNull();
        assertThat(result.getMatrix()).isNull();
        assertThat(result.getVector()).isEqualTo(new BigDecimal[]{new BigDecimal(20), new BigDecimal(16)});
    }
}
