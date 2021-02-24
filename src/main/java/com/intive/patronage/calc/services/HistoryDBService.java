package com.intive.patronage.calc.services;

import java.util.ArrayList;
import java.util.List;

import com.intive.patronage.calc.model.CalcOperation;
import com.intive.patronage.calc.repository.CalcRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

//@Service
public class HistoryDBService implements HistoryService {

    private final CalcRepository calcRepository;

    @Value("classpath:operations/operations.txt")
    private Resource operations;

    public HistoryDBService(CalcRepository calcRepository) {
        this.calcRepository = calcRepository;
    }

    @Override
    public String send(){
        return "HistoryDBService";
    }


    @Override
    public void addOperation(CalcOperation calcOperation) {
        calcRepository.save(calcOperation);
    }

    @Override
    public void removeAll() {
        calcRepository.deleteAll();
    }

    @Override
    public List<CalcOperation> gelAllOperations() {
        return calcRepository.findAll();
    }

    @Override
    public List<CalcOperation> getOperationsFromRange(String filename, long start, long end) {
        if (end == 0) {
            end = calcRepository.findTopByOrderByIdDesc().getId();
        }
        List<Long> idsList = new ArrayList<>();
        for (long i = start; i <= end; i++) {
            idsList.add(i);
        }
        return calcRepository.findAllById(idsList);
    }

    @Override
    public Resource getPossibleOperationsFile() {
        return operations;
    }
}
