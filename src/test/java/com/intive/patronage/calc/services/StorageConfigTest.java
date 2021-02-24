package com.intive.patronage.calc.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.intive.patronage.calc.config.StorageConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class StorageConfigTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

	@Test
	public void whenValueSetToDB_thenCreateHistoryDBService() {
		this.contextRunner.withPropertyValues("calc.historyStorage=file;")
				.withUserConfiguration(StorageConfig.class)
				.run(context -> {
					assertThat(context).hasBean("historyFileService");
					HistoryService historyService = context.getBean(HistoryFileService.class);
					assertThat(historyService.send()).isEqualTo("HistoryFileService");
					assertThat(context).doesNotHaveBean("historyDBService");
				});
	}

}
