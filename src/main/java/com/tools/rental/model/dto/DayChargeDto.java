package com.tools.rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DayChargeDto {

    private String checkOutDate;
    private int rentalDays;
    private Double dailyRentalCharge;
    private List<String> daysDailyChargeApplies = new ArrayList<>();
    private boolean isNoChargeOnHoliday;
    private boolean isNoChargeOnWeekend;
    private int holidayDiscountInPercentage;
    private int weekendDiscountInPercentage;
    private boolean weekendDiscountON;
    private boolean HolidayDiscountON;
    private String firstMondaySeptOfCheckoutYearsStrDate;
    private String independenceDayOfCheckoutYearsStrDate;
    private int chargeDays;
    private int noChargeDays;
    private List<Date> chargeableDateList = new ArrayList<>();
    private List<Date> nonChargeableDateList = new ArrayList<>();
}
