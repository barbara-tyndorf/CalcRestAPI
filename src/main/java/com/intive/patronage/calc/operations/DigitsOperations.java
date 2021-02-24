package com.intive.patronage.calc.operations;

import com.intive.patronage.calc.errors.OperationTypeException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DigitsOperations {

    private BigDecimal a;

    private BigDecimal b;

    public DigitsOperations(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    private BigDecimal add() {
        return a.add(b);
    }

    private BigDecimal sub() {
        return a.subtract(b);
    }

    private BigDecimal multi() {
        return a.multiply(b);
    }

    private BigDecimal div() {
        return a.divide(b, 2, RoundingMode.UNNECESSARY);
    }

    private BigDecimal pow() {
        return a.pow(b.intValue());
    }

    private BigDecimal sqrt() {
        MathContext mc = new MathContext(10);
        return a.sqrt(mc);
    }

    public static BigDecimal calculate(BigDecimal a, BigDecimal b, char operation) {
        DigitsOperations digitsOperation = new DigitsOperations(a, b);
        switch (operation) {
            case '+':
                return digitsOperation.add();
            case '-':
                return digitsOperation.sub();
            case '*':
                return digitsOperation.multi();
            case '/':
                return digitsOperation.div();
            case '^':
                return digitsOperation.pow();
            case '#':
                return digitsOperation.sqrt();
            default:
                throw new OperationTypeException();
        }
    }
}