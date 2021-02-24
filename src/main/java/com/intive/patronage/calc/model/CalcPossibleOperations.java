package com.intive.patronage.calc.model;

import java.util.HashSet;
import java.util.Set;

public class CalcPossibleOperations {

    private Set<CalcOperationDefinition> operationDefinitions;

    private static final CalcPossibleOperations INSTANCE = new CalcPossibleOperations();

    private CalcPossibleOperations() {
        operationDefinitions = new HashSet<>();
        // digit operations
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, DataType.DIGIT, CalcOperationType.ADD));
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, DataType.DIGIT, CalcOperationType.SUB));
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, DataType.DIGIT, CalcOperationType.MUL));
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, DataType.DIGIT, CalcOperationType.DIV));
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, DataType.DIGIT, CalcOperationType.POW));
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, null, CalcOperationType.SQRT));
        // vector operations
        operationDefinitions.add(new CalcOperationDefinition(DataType.VECTOR, DataType.VECTOR, CalcOperationType.ADD));
        operationDefinitions.add(new CalcOperationDefinition(DataType.VECTOR, DataType.VECTOR, CalcOperationType.SUB));
        operationDefinitions.add(new CalcOperationDefinition(DataType.VECTOR, DataType.DIGIT, CalcOperationType.MUL));
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, DataType.VECTOR, CalcOperationType.MUL));
        // matrix operations
        operationDefinitions.add(new CalcOperationDefinition(DataType.MATRIX, DataType.MATRIX, CalcOperationType.ADD));
        operationDefinitions.add(new CalcOperationDefinition(DataType.MATRIX, DataType.MATRIX, CalcOperationType.SUB));
        operationDefinitions.add(new CalcOperationDefinition(DataType.MATRIX, DataType.MATRIX, CalcOperationType.MUL));
        operationDefinitions.add(new CalcOperationDefinition(DataType.MATRIX, DataType.VECTOR, CalcOperationType.MUL));
        operationDefinitions.add(new CalcOperationDefinition(DataType.MATRIX, DataType.DIGIT, CalcOperationType.MUL));
        operationDefinitions.add(new CalcOperationDefinition(DataType.DIGIT, DataType.MATRIX, CalcOperationType.MUL));
    }

    public static Set<CalcOperationDefinition> listDefinitions() {
        return INSTANCE.operationDefinitions;
    }

    public static boolean isOperationSupported(CalcOperation calcOperation) {
        return INSTANCE.operationDefinitions.contains(getDefinition(calcOperation));
    }

    private static CalcOperationDefinition getDefinition(CalcOperation calcOperation) {
        DataType a = null;
        DataType b = null;
        if (calcOperation.getInput().getDigitA() != null) {
            a = DataType.DIGIT;
        } else if (calcOperation.getInput().getVectorA() != null) {
            a = DataType.VECTOR;
        } else if (calcOperation.getInput().getMatrixA() != null) {
            a = DataType.MATRIX;
        }
        if (calcOperation.getInput().getDigitB() != null) {
            b = DataType.DIGIT;
        } else if (calcOperation.getInput().getVectorB() != null) {
            b = DataType.VECTOR;
        } else if (calcOperation.getInput().getMatrixB() != null) {
            b = DataType.MATRIX;
        }
        return new CalcOperationDefinition(a,b,calcOperation.getInput().getOperation());
    }

}
