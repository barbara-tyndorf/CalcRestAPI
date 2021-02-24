package com.intive.patronage.calc.operations;

import java.math.BigDecimal;

public class MixedOperations {

    private BigDecimal[][] matrix;

    private BigDecimal[] vector;

    private BigDecimal a;

    public MixedOperations(BigDecimal[][] matrix, BigDecimal[] vector, BigDecimal a) {
        this.matrix = matrix;
        this.vector = vector;
        this.a = a;
    }

    public static BigDecimal[] multiMatrixByVector(BigDecimal[][] matrix, BigDecimal[] vector) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        BigDecimal[] result = new BigDecimal[rows];

        for (int i = 0; i < rows; i++) {
            BigDecimal temp = new BigDecimal(0);
            for (int j = 0; j < columns; j++) {
                temp = temp.add(matrix[i][j].multiply(vector[j]));
            }
            result[i] = temp;
        }

        return result;
    }

    public static BigDecimal[][] multiMatrixByDigit(BigDecimal[][] matrix, BigDecimal a) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        BigDecimal[][] result = new BigDecimal[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j].multiply(a);
            }
        }
        return result;
    }

    public static BigDecimal[] multiVectorByDigit(BigDecimal[] vector, BigDecimal a) {
        int len = vector.length;
        BigDecimal[] result = new BigDecimal[len];

        for (int i = 0; i < len; i++) {
            result[i] = vector[i].multiply(a);
        }
        return result;
    }
}