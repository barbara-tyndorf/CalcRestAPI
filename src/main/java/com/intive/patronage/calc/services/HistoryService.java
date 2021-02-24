package com.intive.patronage.calc.services;

import java.util.List;
import java.util.Map;

import com.intive.patronage.calc.model.CalcOperation;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface HistoryService {

    void addOperation(CalcOperation calcOperation);

    void removeAll();

    List<CalcOperation> gelAllOperations();

    List<CalcOperation> getOperationsFromRange(Map<String, String> params);

    Resource getPossibleOperationsFile();

    List<String> getPossibleRange();
}
