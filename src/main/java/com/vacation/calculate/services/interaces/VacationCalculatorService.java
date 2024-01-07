package com.vacation.calculate.services.interaces;

import java.time.LocalDate;

public interface VacationCalculatorService {

    Double vacationPayCalculate(Double salaryAverage,
                                LocalDate dateFrom,
                                LocalDate dateTo);
}
