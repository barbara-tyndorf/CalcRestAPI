package com.intive.patronage.calc.services;

import com.intive.patronage.calc.config.CalcConfig;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class HistoryFileServiceTest {

    @Mock
    CalcConfig calcConfig;

    @InjectMocks
    HistoryFileService historyFileService = new HistoryFileService(calcConfig);

    //fixme init historyService
//    @Test
//    void save() {
//
//        CalcInput input = new CalcInput();
//        input.setDigitA(new BigDecimal(2));
//        input.setDigitB(new BigDecimal(2));
//        input.setOperation(CalcOperationType.ADD);
//
//        CalcResult result = CalcResult.builder().digit(new BigDecimal(4)).build();
//
//        CalcOperation calcOperation = new CalcOperation(input, result);
//
//        historyService.save(calcOperation);
//
//        assertTrue(new File(System.getProperty("java.io.tmpdir") + "history" + File.separator + "historia_obliczeń.txt").exists());
//    }
}
