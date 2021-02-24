package com.intive.patronage.calc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalcOperation {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private CalcInput input;

    @Embedded
    private CalcResult result;

}
