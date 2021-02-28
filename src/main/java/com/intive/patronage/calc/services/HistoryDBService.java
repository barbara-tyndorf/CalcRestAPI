package com.intive.patronage.calc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.intive.patronage.calc.errors.FileNotAvailableException;
import com.intive.patronage.calc.errors.IdNumberException;
import com.intive.patronage.calc.errors.NoOperationException;
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
    public List<CalcOperation> getAllOperations() {
        return calcRepository.findAll();
    }

    @Override
    public List<CalcOperation> getOperationsFromRange(Long start, Long end) {
        if (calcRepository.findAll().isEmpty()) {
            throw new NoOperationException();
        }
        if ((start < calcRepository.findTopByOrderByIdAsc().getId()
                || start > calcRepository.findTopByOrderByIdDesc().getId())
                || (end != null && (end < calcRepository.findTopByOrderByIdAsc().getId()
                || end > calcRepository.findTopByOrderByIdDesc().getId()))) {
            throw new IdNumberException();
        }
        if (end == null) {
            end = calcRepository.findTopByOrderByIdDesc().getId();
        }
        List<Long> idsList = new ArrayList<>();
        for (Long i = start; i <= end; i++) {
            idsList.add(i);
        }
        return calcRepository.findAllById(idsList);
    }

    @Override
    public Resource getPossibleOperationsFile() {
        return operations;
    }

    @Override
    public Resource getFile(String filename) {
        throw new FileNotAvailableException();
    }

}
