package com.intive.patronage.calc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "calc")
public class CalcConfig {
    private Integer vectorMaxSize;
    private Integer matrixMaxRows;
    private Integer matrixMaxColumns;
    private Integer digitMinExpo;
    private Integer digitMaxExpo;
    private Integer fileMaxLog;
}