package com.vacation.calculate.controllers;

import com.vacation.calculate.services.interaces.VacationCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;


@RestController
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;

    @Autowired
    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> vacationPay(@RequestParam Double salaryAverage,
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

        return new ResponseEntity<>("Vacation Payments: " + vacationCalculatorService.vacationPayCalculate(salaryAverage, dateFrom, dateTo), HttpStatus.OK);
    }
}
