package com.intive.patronage.calc.config;

import com.intive.patronage.calc.repository.CalcRepository;
import com.intive.patronage.calc.services.HistoryDBService;
import com.intive.patronage.calc.services.HistoryFileService;
import com.intive.patronage.calc.services.HistoryService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

	private CalcConfig calcConfig;
	private CalcRepository calcRepository;

	public StorageConfig(CalcConfig calcConfig, CalcRepository calcRepository) {
		this.calcConfig = calcConfig;
		this.calcRepository = calcRepository;
	}

	@Bean(name = "historyFileService")
	@ConditionalOnProperty(prefix = "calc", name = "historyStorage", havingValue = "file")
//	@ConditionalOnProperty(name = "calc.historyStorage", havingValue = "file")
	public HistoryService historyService() {
		return new HistoryFileService(calcConfig);
	}

	@Bean(name = "historyDBService")
	@ConditionalOnProperty(prefix = "calc", name = "historyStorage", havingValue = "db")
	public HistoryService historyService2() {
		return new HistoryDBService(calcRepository);
	}
}
