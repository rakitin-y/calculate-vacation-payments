package com.vacation.calculate.services.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Holidays {
    NEW_YEAR(LocalDate.parse(LocalDateTime.now().getYear() + "-01-01"),
            LocalDate.parse(LocalDateTime.now().getYear() + "-01-08")),
    FATHERLAND_DEFENDERS_DAY(LocalDate.parse(LocalDateTime.now().getYear() + "-02-23"),
            LocalDate.parse(LocalDateTime.now().getYear() + "-02-24")),
    INTERNATIONAL_WOMEN_DAY(LocalDate.parse(LocalDateTime.now().getYear() + "-03-08"),
            LocalDate.parse(LocalDateTime.now().getYear() + "-03-08")),
    FIRST_OF_MAY(LocalDate.parse(LocalDateTime.now().getYear() + "-05-08"),
            LocalDate.parse(LocalDateTime.now().getYear() + "-05-09")),
    VICTORY_DAY(LocalDate.parse(LocalDateTime.now().getYear() + "-01-01"),
            LocalDate.parse(LocalDateTime.now().getYear() + "-01-10")),
    RUSSIA_DAY(LocalDate.parse(LocalDateTime.now().getYear() + "-06-12"),
            LocalDate.parse(LocalDateTime.now().getYear() + "-06-12")),
    NATIONAL_UNITY_DAY(LocalDate.parse(LocalDateTime.now().getYear() + "-11-06"),
            LocalDate.parse(LocalDateTime.now().getYear() + "-11-06"));


    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    Holidays(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public static List<List<LocalDate>> fillHolidays() {
        List<List<LocalDate>> allHolidays = new ArrayList<>();
        for (Holidays holidays : Holidays.values()) {
            allHolidays.add(holidays.dateFrom.datesUntil(holidays.dateTo.plusDays(1))
                    .collect(Collectors.toList()));
        }
        return allHolidays;
    }
}
