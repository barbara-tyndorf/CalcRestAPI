package com.intive.patronage.calc.operations;

import com.intive.patronage.calc.errors.OperationTypeException;

import java.math.BigDecimal;

public class MatrixOperations {

    private BigDecimal[][] matrix1;

    private BigDecimal[][] matrix2;

    public MatrixOperations(BigDecimal[][] matrix1, BigDecimal[][] matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    private BigDecimal[][] add() {
        int rows = matrix1.length;
        int columns = matrix1[0].length;
        BigDecimal[][] result = new BigDecimal[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j].add(matrix2[i][j]);
            }
        }
        return result;
    }

    private BigDecimal[][] sub() {
        int rows = matrix1.length;
        int columns = matrix1[0].length;
        BigDecimal[][] result = new BigDecimal[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j].subtract(matrix2[i][j]);
            }
        }
        return result;
    }

    private BigDecimal[][] multi() {
        int row = matrix1.length;
        int col1 = matrix1[0].length;
        int col2 = matrix2[0].length;
        BigDecimal[][] result = new BigDecimal[row][col2];

        BigDecimal temp = BigDecimal.ZERO;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col1; k++)
                    temp = temp.add(matrix1[i][k].multiply(matrix2[k][j]));
                result[i][j] = temp;
                temp = BigDecimal.ZERO;
            }
        }
        return result;
    }

    public static BigDecimal[][] calculate(BigDecimal[][] matrix1, BigDecimal[][] matrix2, char operation) {
        MatrixOperations matrixOperations = new MatrixOperations(matrix1, matrix2);
        switch (operation) {
            case '+':
                return matrixOperations.add();
            case '-':
                return matrixOperations.sub();
            case '*':
                return matrixOperations.multi();
            default:
                throw new OperationTypeException();
        }
    }
}