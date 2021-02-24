package com.intive.patronage.calc.operations;

import com.intive.patronage.calc.errors.OperationTypeException;

import java.math.BigDecimal;

public class VectorOperations {

    private BigDecimal[] vector1;

    private BigDecimal[] vector2;

    public VectorOperations(BigDecimal[] vector1, BigDecimal[] vector2) {
        this.vector1 = vector1;
        this.vector2 = vector2;
    }

    private BigDecimal[] add() {
        int len = vector1.length;
        BigDecimal[] result = new BigDecimal[len];

        for (int i = 0; i < len; i++) {
            result[i] = vector1[i].add(vector2[i]);
        }

        return result;
    }

    private BigDecimal[] sub() {
        int len = vector1.length;
        BigDecimal[] result = new BigDecimal[len];

        for (int i = 0; i < len; i++) {
            result[i] = vector1[i].subtract(vector2[i]);
        }
        return result;
    }

    public static BigDecimal[] calculate(BigDecimal[] vector1, BigDecimal[] vector2, char operation) {
        VectorOperations vectorOperations = new VectorOperations(vector1, vector2);
        switch (operation) {
            case '+':
                return vectorOperations.add();
            case '-':
                return vectorOperations.sub();
            default:
                throw new OperationTypeException();
        }
    }
}