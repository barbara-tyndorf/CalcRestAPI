package com.intive.patronage.calc.validation;

import java.math.BigDecimal;
import java.util.Arrays;

import com.intive.patronage.calc.config.CalcConfig;
import com.intive.patronage.calc.model.CalcInput;
import com.intive.patronage.calc.model.CalcOperation;
import com.intive.patronage.calc.model.CalcOperationType;
import com.intive.patronage.calc.model.CalcPossibleOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class CalcInputValidator implements Validator {

    private CalcConfig calcConfig;

    public CalcInputValidator(CalcConfig calcConfig) {
        this.calcConfig = calcConfig;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CalcInput.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        log.debug("Validating {}", target);

        if (isEmpty(errors.getFieldValue("digitA")) && isEmpty(errors.getFieldValue("vectorA")) && isEmpty(
                errors.getFieldValue("matrixA"))) {
            errors.reject("Podaj jedną wartość A (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (!isEmpty(errors.getFieldValue("digitA")) && !isEmpty(errors.getFieldValue("vectorA")) && !isEmpty(
                errors.getFieldValue("matrixA"))) {
            errors.reject("Podaj tylko jedną wartość A (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (!isEmpty(errors.getFieldValue("digitA")) && !isEmpty(errors.getFieldValue("vectorA")) && isEmpty(
                errors.getFieldValue("matrixA"))) {
            errors.reject("Podaj tylko jedną wartość A (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (isEmpty(errors.getFieldValue("digitA")) && !isEmpty(errors.getFieldValue("vectorA")) && !isEmpty(
                errors.getFieldValue("matrixA"))) {
            errors.reject("Podaj tylko jedną wartość A (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (!isEmpty(errors.getFieldValue("digitA")) && isEmpty(errors.getFieldValue("vectorA")) && !isEmpty(
                errors.getFieldValue("matrixA"))) {
            errors.reject("Podaj tylko jedną wartość A (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (isEmpty(errors.getFieldValue("operation"))) {
            errors.reject("Podaj typ operacji do wykonania.");
            return;
        }
        if (!isEmpty(errors.getFieldValue("digitB")) && !isEmpty(errors.getFieldValue("vectorB")) && !isEmpty(
                errors.getFieldValue("matrixB"))) {
            errors.reject("Podaj jedną wartość B (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (!isEmpty(errors.getFieldValue("digitB")) && !isEmpty(errors.getFieldValue("vectorB")) && !isEmpty(
                errors.getFieldValue("matrixB"))) {
            errors.reject("Podaj tylko jedną wartość B (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (!isEmpty(errors.getFieldValue("digitB")) && !isEmpty(errors.getFieldValue("vectorB")) && isEmpty(
                errors.getFieldValue("matrixB"))) {
            errors.reject("Podaj tylko jedną wartość B (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (isEmpty(errors.getFieldValue("digitB")) && !isEmpty(errors.getFieldValue("vectorB")) && !isEmpty(
                errors.getFieldValue("matrixB"))) {
            errors.reject("Podaj tylko jedną wartość B (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }
        if (!isEmpty(errors.getFieldValue("digitB")) && isEmpty(errors.getFieldValue("vectorB")) && !isEmpty(
                errors.getFieldValue("matrixB"))) {
            errors.reject("Podaj tylko jedną wartość B (liczbę rzeczywistą, wektor lub macierz).");
            return;
        }

        CalcInput calcInput = (CalcInput) target;

        if (calcInput.getDigitA() != null && calcInput.getDigitB() != null && calcInput.getOperation()
                .equals(CalcOperationType.POW)) {
            if (calcInput.getDigitB().compareTo(new BigDecimal(calcConfig.getDigitMinExpo())) < 0
                    || calcInput.getDigitB().compareTo(new BigDecimal(calcConfig.getDigitMaxExpo())) > 0) {
                errors.reject(String.format("Wykładnik musi być z zakresu <%d,%d>", calcConfig.getDigitMinExpo(),
                        calcConfig.getDigitMaxExpo()));
            }
        }
        if (calcInput.getDigitA() != null && calcInput.getDigitB() != null && calcInput.getOperation()
                .equals(CalcOperationType.DIV)) {
            if (calcInput.getDigitB().compareTo(BigDecimal.ZERO) == 0) {
                errors.reject("Dzielenie przez 0 jest niemożliwe, spróbuj jeszcze raz.");
            }
        }
        if (calcInput.getDigitA() != null && calcInput.getDigitB() != null && calcInput.getOperation()
                .equals(CalcOperationType.SQRT)) {
            errors.reject("Przy pierwiastkowaniu podaj tylko wartość A.");
        }
        if (calcInput.getVectorA() != null && calcInput.getVectorA().length > calcConfig.getVectorMaxSize()) {
            errors.reject(String.format("Wektor A może mieć maksymalnie %d", calcConfig.getVectorMaxSize()));
        }
        if (calcInput.getVectorB() != null && calcInput.getVectorB().length > calcConfig.getVectorMaxSize()) {
            errors.reject(String.format("Wektor B może mieć maksymalnie %d", calcConfig.getVectorMaxSize()));
        }
        if (calcInput.getVectorA() != null && calcInput.getVectorB() != null) {
            if (calcInput.getOperation().equals(CalcOperationType.ADD) || calcInput.getOperation()
                    .equals(CalcOperationType.SUB)) {
                if (calcInput.getVectorA().length != calcInput.getVectorB().length) {
                    errors.reject("Nie można wykonać operacji. Wektory muszą mieć taką samą ilość elementów.");
                }
            }
        }

        if (calcInput.getMatrixA() != null && calcInput.getVectorB() != null) {
            if (calcInput.getMatrixA()[0].length != calcInput.getVectorB().length) {
                errors.reject("Nie można wykonać operacji. Liczba elementów wektora musi być równa liczbie kolumn macierzy.");
            }
        }

        if (calcInput.getMatrixA() != null) {
            if (calcInput.getMatrixA().length > calcConfig.getMatrixMaxColumns()) {
                errors.reject(String.format("Macierz A może mieć maksymalnie %d kolumn.", calcConfig.getMatrixMaxColumns()));
            }
            if (Arrays.stream(calcInput.getMatrixA()).anyMatch(row -> row.length > calcConfig.getMatrixMaxRows())) {
                errors.reject(String.format("Macierz A może mieć maksymalnie %d wiersze.", calcConfig.getMatrixMaxRows()));
            }
        }
        if (calcInput.getMatrixB() != null) {
            if (calcInput.getMatrixB().length > calcConfig.getMatrixMaxColumns()) {
                errors.reject(String.format("Macierz B może mieć maksymalnie %d kolumn.", calcConfig.getMatrixMaxColumns()));
            }
            if (Arrays.stream(calcInput.getMatrixB()).anyMatch(row -> row.length > calcConfig.getMatrixMaxRows())) {
                errors.reject(String.format("Macierz B może mieć maksymalnie %d wiersze.", calcConfig.getMatrixMaxRows()));
            }
        }
        if (calcInput.getMatrixA() != null && calcInput.getMatrixB() != null) {
            if (calcInput.getOperation().equals(CalcOperationType.ADD) || calcInput.getOperation().equals(CalcOperationType.SUB)) {
                if (calcInput.getMatrixA().length != calcInput.getMatrixB().length) {
                    errors.reject("Nie można wykonać operacji. Macierze muszą mieć tyle samo kolumn.");
                }
                if (calcInput.getMatrixA()[0].length != calcInput.getMatrixB()[0].length) {
                    errors.reject("Nie można wykonać operacji. Macierze muszą mieć tyle samo wierszy.");
                }
            }
            if (calcInput.getOperation().equals(CalcOperationType.MUL)) {
                if (calcInput.getMatrixA().length != calcInput.getMatrixB()[0].length
                        || calcInput.getMatrixA()[0].length != calcInput.getMatrixB().length) {
                    errors.reject("Nie można wykonać operacji. Liczba elementów w wierszu macierzy A musi być równa "
                            + "liczbie elementów w kolumnie macierzy B.");
                }
            }
        }

        if (!CalcPossibleOperations.isOperationSupported(new CalcOperation(null, (CalcInput) target, null))) {
            errors.reject(
                    "Nie można wykonać operacji. Zapoznaj się z plikiem operations.txt wywołując polecenie <getPossibleOperations>.");
        }
    }

    public static boolean isEmpty(Object value) {
        return value == null || value.toString().trim().equals("");
    }
}
