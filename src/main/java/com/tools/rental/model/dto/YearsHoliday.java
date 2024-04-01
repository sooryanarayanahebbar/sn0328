package com.tools.rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YearsHoliday {

    private int year;
    private int twoDigitYear;
    private String firstMondaySeptStrDate;
    private String independenceDayStrDate;
    private String IndDayOfWeek; //SUN. MON, etc
    private boolean isIndependenceDayOnWeekend;
}
