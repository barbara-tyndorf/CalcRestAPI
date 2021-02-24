package com.intive.patronage.calc.services;

import java.util.List;

import com.intive.patronage.calc.model.CalcOperation;
import org.springframework.core.io.Resource;

//@Service
public interface HistoryService {

    void addOperation(CalcOperation calcOperation);

    void removeAll();

    List<CalcOperation> gelAllOperations();

    List<CalcOperation> getOperationsFromRange(String filename, long start, long end);

    Resource getPossibleOperationsFile();

    String send();
}
