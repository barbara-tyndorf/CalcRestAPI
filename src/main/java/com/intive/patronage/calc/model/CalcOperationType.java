package com.intive.patronage.calc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CalcOperationType {

    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    POW("^"),
    SQRT("#");

    private final String op;

    CalcOperationType(String op) {
        this.op = op;
    }

    @JsonValue
    public String getOp() {
        return op;
    }

    @JsonCreator
    public static CalcOperationType fromString(String op) {
        for (CalcOperationType operation : CalcOperationType.values()) {
            if (operation.op.equals(op)) {
                return operation;
            }
        }
        return null;
    }
}
