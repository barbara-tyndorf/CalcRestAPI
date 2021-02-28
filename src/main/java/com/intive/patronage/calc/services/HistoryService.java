package com.intive.patronage.calc.services;

import java.util.List;

import com.intive.patronage.calc.model.CalcOperation;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface HistoryService {

    void addOperation(CalcOperation calcOperation);

    void removeAll();

    List<CalcOperation> gelAllOperations();

    List<CalcOperation> getOperationsFromRange(Long start, Long end);

    Resource getPossibleOperationsFile();

    List<String> getPossibleRange();
}
