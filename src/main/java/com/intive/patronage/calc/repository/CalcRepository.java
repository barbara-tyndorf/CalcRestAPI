package com.intive.patronage.calc.repository;

import com.intive.patronage.calc.model.CalcOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalcRepository extends JpaRepository<CalcOperation, Long> {

    CalcOperation findTopByOrderByIdDesc();
}
