package com.intive.patronage.calc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.intive.patronage.calc.errors.IdNumberException;
import com.intive.patronage.calc.model.CalcOperation;
import com.intive.patronage.calc.repository.CalcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@ConditionalOnProperty(prefix = "calc", name = "history-service", havingValue = "db")
@Service
public class HistoryDBService implements HistoryService {

    private final CalcRepository calcRepository;

    @Value("classpath:operations/operations.txt")
    private Resource operations;

    public HistoryDBService(CalcRepository calcRepository) {
        this.calcRepository = calcRepository;
    }

    @Override
    public List<String> getPossibleRange() {
        return calcRepository.findAll().stream()
                .map((o) -> o.getId().toString())
                .collect(Collectors.toList());
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
    public List<CalcOperation> getOperationsFromRange(Map<String, String> params) {
        long start = 0;
        long end = calcRepository.findTopByOrderByIdDesc().getId();
        try {
            if (params.containsKey("start")) {
                start = Long.parseLong(params.get("start"));
            }
            if (params.containsKey("end")) {
                start = Long.parseLong(params.get("end"));
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            throw new IdNumberException();
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
