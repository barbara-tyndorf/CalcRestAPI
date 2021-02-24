package com.intive.patronage.calc.services;

import java.math.BigDecimal;

import com.intive.patronage.calc.errors.OperationImpossibleException;
import com.intive.patronage.calc.model.CalcInput;
import com.intive.patronage.calc.model.CalcOperationType;
import com.intive.patronage.calc.model.CalcResult;
import com.intive.patronage.calc.operations.DigitsOperations;
import com.intive.patronage.calc.operations.MatrixOperations;
import com.intive.patronage.calc.operations.MixedOperations;
import com.intive.patronage.calc.operations.VectorOperations;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

    public CalcResult calc(CalcInput input) {
        char operation = input.getOperation().getOp().charAt(0);

        if (input.getDigitA() != null && input.getDigitB() != null) {
            BigDecimal result = DigitsOperations.calculate(input.getDigitA(), input.getDigitB(), operation);
            return CalcResult.builder().digit(result).build();
        } else if (input.getDigitA() != null && input.getDigitB() == null && input.getOperation().equals(CalcOperationType.SQRT)) {
            BigDecimal result = DigitsOperations.calculate(input.getDigitA(), input.getDigitB(), operation);
            return CalcResult.builder().digit(result).build();
        } else if (input.getDigitA() != null && input.getVectorB() != null) {
            BigDecimal[] result = MixedOperations.multiVectorByDigit(input.getVectorB(), input.getDigitA());
            return CalcResult.builder().vector(result).build();
        } else if (input.getVectorA() != null && input.getDigitB() != null) {
            BigDecimal[] result = MixedOperations.multiVectorByDigit(input.getVectorA(), input.getDigitB());
            return CalcResult.builder().vector(result).build();
        } else if (input.getDigitA() != null && input.getMatrixB() != null) {
            BigDecimal[][] result = MixedOperations.multiMatrixByDigit(input.getMatrixB(), input.getDigitA());
            return CalcResult.builder().matrix(result).build();
        } else if (input.getMatrixA() != null && input.getDigitB() != null) {
            BigDecimal[][] result = MixedOperations.multiMatrixByDigit(input.getMatrixA(), input.getDigitB());
            return CalcResult.builder().matrix(result).build();
        } else if (input.getMatrixA() != null && input.getVectorB() != null) {
            BigDecimal[] result = MixedOperations.multiMatrixByVector(input.getMatrixA(), input.getVectorB());
            return CalcResult.builder().vector(result).build();
        } else if (input.getVectorA() != null && input.getMatrixB() != null) {
            BigDecimal[] result = MixedOperations.multiMatrixByVector(input.getMatrixB(), input.getVectorA());
            return CalcResult.builder().vector(result).build();
        } else if (input.getVectorA() != null && input.getVectorB() != null) {
            BigDecimal[] result = VectorOperations.calculate(input.getVectorA(), input.getVectorB(), operation);
            return CalcResult.builder().vector(result).build();
        } else if (input.getMatrixA() != null && input.getMatrixB() != null) {
            BigDecimal[][] result = MatrixOperations.calculate(input.getMatrixA(), input.getMatrixB(), operation);
            return CalcResult.builder().matrix(result).build();
        } else {
            throw new OperationImpossibleException();
        }
    }
}
