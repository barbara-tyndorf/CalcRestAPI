package com.intive.patronage.calc;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import com.intive.patronage.calc.model.CalcInput;
import com.intive.patronage.calc.model.CalcOperation;
import com.intive.patronage.calc.model.CalcResult;
import com.intive.patronage.calc.services.CalcService;
import com.intive.patronage.calc.services.HistoryFileService;
import com.intive.patronage.calc.validation.CalcInputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Slf4j
@RequestMapping("/calc")
@RestController
public class CalcController {

    private final CalcService calcService;
    private final HistoryFileService historyService;
    private final CalcInputValidator calcInputValidator;

    @InitBinder
    public void initPetBinder(DataBinder dataBinder) {
        dataBinder.addValidators(calcInputValidator);
    }

    public CalcController(CalcService calcService, HistoryFileService historyService, CalcInputValidator calcInputValidator) {
        this.calcService = calcService;
        this.historyService = historyService;
        this.calcInputValidator = calcInputValidator;
    }

    @PostMapping
    public ResponseEntity<CalcResult> calc(@Valid @RequestBody CalcInput calcInput) throws IOException {
        log.info("Calculation for {}", calcInput);
        CalcResult result = calcService.calc(calcInput);
        CalcOperation calcOperation = new CalcOperation(null, calcInput, result);
        historyService.addOperation(calcOperation);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = {"text/plain; charset=utf-8"})
    @ResponseBody
    public ResponseEntity<Resource> getPossibleOperations() {
        return ResponseEntity.ok(historyService.getPossibleOperationsFile());
    }

    @GetMapping("/history")
    public List<CalcOperation> getOperationsHistory() {
        return historyService.gelAllOperations();
    }

    @GetMapping(path = "/history/{filename}", produces = "application/json")
    public List<CalcOperation> getOperationsFromSpecificFile(@PathVariable String filename) {
        return historyService.getOperationsFromRange(filename, 0, 0);
    }

    @GetMapping("/history/files")
    public List<String> getAvailableFiles() {
        return historyService.listFiles();
    }

    @DeleteMapping("/history")
    public ResponseEntity deleteAllOperations() {
        historyService.removeAll();
        return ResponseEntity.ok().build();
    }
}
