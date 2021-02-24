package com.intive.patronage.calc.model;

import lombok.Value;

@Value
public class CalcOperationDefinition {

    private DataType a;

    private DataType b;

    private CalcOperationType operation;
}