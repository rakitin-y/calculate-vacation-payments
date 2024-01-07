package com.vacation.calculate.services.implementation;

import com.vacation.calculate.services.entity.Holidays;
import com.vacation.calculate.services.interaces.VacationCalculatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    @Override
    public Double vacationPayCalculate(Double salaryAverage,
                                       LocalDate dateFrom,
                                       LocalDate dateTo) {

        if (salaryAverage < 554.33) salaryAverage = 554.33;
        int days = calculateNotFreeDays(dateFrom, dateTo);
        return salaryAverage * days;

    }

    private int calculateNotFreeDays(LocalDate dateFrom,
                                     LocalDate dateTo) {
        int notFreeDays = 0;
        List<List<LocalDate>> allHolidays = Holidays.fillHolidays();
        List<LocalDate> vacationDays = dateFrom.datesUntil(dateTo.plusDays(1))
                .collect(Collectors.toList());

        for (LocalDate day : vacationDays) {
            String dayOfWeek = day.getDayOfWeek().name();
            if (!dayOfWeek.startsWith("SAT") &&
                    !dayOfWeek.startsWith("SUN") &&
                    allHolidays.stream().noneMatch(holidays -> Objects.equals(false, isItHoliday(holidays, day)))) {
                notFreeDays++;
            }
        }

        return notFreeDays;
    }

    private boolean isItHoliday(List<LocalDate> allHolidays, LocalDate day) {
        return !allHolidays.contains(day);
    }
}
