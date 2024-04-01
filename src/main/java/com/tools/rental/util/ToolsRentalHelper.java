package com.tools.rental.util;

import com.tools.rental.AppConstants;
import com.tools.rental.model.dto.DayChargeDto;
import com.tools.rental.model.dto.YearsHoliday;
import com.tools.rental.order.OrderDto;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;

public class ToolsRentalHelper {

    public static Map<Integer, YearsHoliday> yearlyHolidayCachedMap = new HashMap<>();


    public static List<String> validateOrders(OrderDto dto) {
        List<OrderDto> listDto = new ArrayList<>();
        listDto.add(dto);
        return validateOrders(listDto);
    }

    public static List<String> validateOrders(List<OrderDto> listDto) {
        List<String> errors = new ArrayList<>();

        for (OrderDto dto : listDto) {
            if (null == dto) {
                errors.add("Please place the Order");
                return errors;
            }
            if (null == dto.getToolCode() || "".equals(dto.getToolCode().trim())) {
                errors.add("toolCode is required field.");
            }

            if (dto.getRentalDays() < 1) {
                errors.add("rentalDays is required field. Min value would be 1");
            }
            if (dto.getRentalDays() > 999) {
                errors.add("rentalDays is too big");
            }

            if (dto.getDiscount() < 1 || dto.getDiscount() > 100) {
                errors.add("Please enter valid value. Discount input range between 1 to 100");
            }

            if (null == dto.getCheckOutDate()) {
                errors.add("Checkout Date is required field.");
            }
            if (null == dto.getCheckOutDate()) {
                errors.add("Checkout Date is required field.");
            }
            if (!isValidDate(dto.getCheckOutDate())) {
                errors.add("Please enter valid date. 'MM/dd/yy' format.");
            }
        }
        ////System.out.println("ValidateOrders Exit");
        ////System.out.println("Errors:" + errors.toString());
        return errors;
    }


    /**
     * @param appDateString | Ex; 05/26/24
     * @return | Boolean
     */
    public static boolean isValidDate(String appDateString) {
        DateFormat sdf = new SimpleDateFormat(AppConstants.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(appDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * @param inputDateString | Ex; 05/26/24
     * @return | 'Sun May 26 00:00:00 IST 2024'
     */
    public static Date convertAppDateStringToDate(String inputDateString) {
        Date date = null;
        DateFormat sdf = new SimpleDateFormat(AppConstants.dateFormat);
        sdf.setLenient(false);
        try {
            date = sdf.parse(inputDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        } catch (Exception ex) {
            ex.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     * @param inputDateString |Ex: 'Sun May 26 00:00:00 IST 2024'
     * @return | Ex; 05/26/24
     */
    public static String appDateFormat(String inputDateString) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern(AppConstants.fullDateTimeFormatterPattern)
                .withLocale(Locale.US);
        ZonedDateTime zdt = ZonedDateTime.parse(inputDateString, f);


        LocalDate ld = zdt.toLocalDate();
        DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern(AppConstants.dateTimeFormatterPattern);
        String output = ld.format(fLocalDate);


        return output;
    }

    public static boolean isWeekDays(String dateStr) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern(AppConstants.fullDateTimeFormatterPattern)
                .withLocale(Locale.US);
        ZonedDateTime zdt = ZonedDateTime.parse(dateStr, f);


        LocalDate ld = zdt.toLocalDate();


        // LocalDate date = LocalDate.now();
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        boolean flag = true;
        switch (day) {
            case SATURDAY:
                ////System.out.println("Weekend - Saturday");
                flag = false;
            case SUNDAY:
                ////System.out.println("Weekend - Sunday");
                flag = false;
            default:
                ////System.out.println("Not a Weekend");
        }
        return flag;
    }

    /* Rental Calculations */


    public static String findDueDate(String checkOutDate, int rentalDays) {
        String dueDate = "";
        try {
            Date newDate = AppConstants.appDateFormatter.parse(checkOutDate);
            Calendar c = Calendar.getInstance();
            c.setTime(newDate);
            c.add(Calendar.DATE, rentalDays - 1); // Adding  days : -1?? , Including Checkout Date and Total rentalDays
            dueDate = AppConstants.appDateFormatter.format(c.getTime());
            ////System.out.println("*********");
            ////System.out.println("checkOutDate: " + checkOutDate);
            ////System.out.println("RentalDays: " + rentalDays);
            ////System.out.println("DueDate: Plus date: " + dueDate);
            ////System.out.println("*********");
            return dueDate;
        } catch (ParseException e) {
            e.getStackTrace();
            return dueDate;
        }
    }


    public static Double findPreDiscountCharge(int chargeDays, Double dailyRentalCharge) {
        /* Note: [ dailyRentalCharge X dailyRentalCharge ] */
        Double preDiscountCharge = (Double.valueOf(dailyRentalCharge * chargeDays));
        return preDiscountCharge;
    }


    public static Double findDiscountAmount(Double preDiscountCharge, int discountPercent) {
        /* Note: (([preDiscountCharge] X [discountPercent]) / 100) : Rounded half upto cents */
        return (Double.valueOf((preDiscountCharge * discountPercent) / 100));
    }

    public static Double findFinalCharge(Double preDiscountCharge, Double discountAmount) {
        /* Note:  [preDiscountCharge] - [discountAmount]  */
        return (Double.valueOf((preDiscountCharge - discountAmount)));
    }

    public static String roundHalfUpAndUSCurrencyFormat(Double amount) {
        BigDecimal result = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(result.doubleValue());
    }


    /*  Find Charge Days Calculations  */


    public static DayChargeDto findChargeDays(DayChargeDto dayChargeDto) {


        int fullYear = Year.now().getValue();
        String[] ckoutDt = dayChargeDto.getCheckOutDate().split("/");
        if (null != ckoutDt && ckoutDt.length == 3) {
            fullYear = new BigDecimal(String.valueOf(fullYear).substring(0, 2) + "" + ckoutDt[2]).intValue();
            ////System.out.println("Checkout Year: " + fullYear);
        }
        YearsHoliday yearlyHoliday = yearlyHolidayCachedMap.get(fullYear);
        if (null == yearlyHoliday) {
            DateUtils.getYearlyINDandFirstSeptMondayHolidays(fullYear, yearlyHolidayCachedMap);
            ////System.out.println("cached: " + fullYear);
            yearlyHoliday = yearlyHolidayCachedMap.get(fullYear);
        }
        dayChargeDto.setFirstMondaySeptOfCheckoutYearsStrDate(yearlyHoliday.getFirstMondaySeptStrDate());
        dayChargeDto.setIndependenceDayOfCheckoutYearsStrDate(yearlyHoliday.getIndependenceDayStrDate());


        int totalNoOfRentalDay = dayChargeDto.getRentalDays();
        DayChargeDto dC = extractIndividualRentalChargeableDates(dayChargeDto.getCheckOutDate(), totalNoOfRentalDay, dayChargeDto);
        dC.setChargeDays(dC.getChargeDays());
       /* System.out.println("" +
                "---------------" +
                "\nTotal Rental Days: " + dC.getRentalDays() + ":" +
                "\nChargeable Days: " + dC.getChargeDays() + "" +
                "\nNoChargeable Days: " + dC.getNoChargeDays() + "" +
                "\nChargeable Days: " + dC.getChargeableDateList() +
                "\nNon Chargeable Days: " + dC.getNonChargeableDateList()
                + "\n---------------\n");
*/

        return dC;
    }

    private static DayChargeDto extractIndividualRentalChargeableDates(String checkOutDate, int totalNoOfRentalDay, DayChargeDto dayChargeDto) {
        //////System.out.println("STEP-11: rentalDays & dateList.size(): "+totalNoOfRentalDay +" : "+dateList.size());
        String today = DateUtils.getDayOfWeek(checkOutDate);
        boolean isWeekendToday = (("SUNDAY".equalsIgnoreCase(today) || "SATURDAY".equalsIgnoreCase(today)));////****
        ////System.out.println("<" + checkOutDate + ">" + " : " + today);
        if (totalNoOfRentalDay > 0) {
            try {
                Date newDate = AppConstants.appDateFormatter.parse(checkOutDate);

                Calendar c = Calendar.getInstance();
                c.setTime(newDate);
                c.add(Calendar.DATE, 1); // +1
                checkOutDate = AppConstants.appDateFormatter.format(c.getTime());

                boolean noChargeStatusIsEnabled = Boolean.FALSE;
                ////System.out.println("STEP-1: " + dayChargeDto.getDaysDailyChargeApplies().contains(today));

                if (dayChargeDto.getDaysDailyChargeApplies().contains(today)) {
                    boolean indDayFlag = DateUtils.isEquals(checkOutDate, dayChargeDto.getIndependenceDayOfCheckoutYearsStrDate());
                    boolean septMondayFlag = DateUtils.isEquals(checkOutDate, dayChargeDto.getFirstMondaySeptOfCheckoutYearsStrDate());
                    ////System.out.println("STEP-2: indDayFlag & septMondayFlag: " + indDayFlag + " : " + septMondayFlag);

                    if ((indDayFlag || septMondayFlag) && dayChargeDto.isNoChargeOnHoliday()) {
                        ////System.out.println("STEP-3: HOLIDAY & isNoChargeOnHoliday: " + dayChargeDto.isNoChargeOnHoliday());

                        noChargeStatusIsEnabled = Boolean.TRUE;
                    } else if ((indDayFlag || septMondayFlag) && !dayChargeDto.isNoChargeOnHoliday()) {
                        ////System.out.println("STEP-4: HOLIDAY & !isNoChargeOnHoliday==false: " + !dayChargeDto.isNoChargeOnHoliday());

                        dayChargeDto.setHolidayDiscountON(Boolean.TRUE);
                        //Add holidayDiscountInPercentage
                        dayChargeDto.setDailyRentalCharge((dayChargeDto.getDailyRentalCharge() * dayChargeDto.getHolidayDiscountInPercentage()) / 100);
                    } else if (isWeekendToday && dayChargeDto.isNoChargeOnWeekend()) {
                        ////System.out.println("STEP-5: this.isWeekend && this.isNoChargeOnWeekend: " + isWeekendToday + " : " + dayChargeDto.isNoChargeOnWeekend());

                        noChargeStatusIsEnabled = Boolean.TRUE;
                    } else if (isWeekendToday && !dayChargeDto.isNoChargeOnWeekend()) {
                        ////System.out.println("STEP-6: this.isWeekend && !this.isNoChargeOnWeekend: " + isWeekendToday + " : " + !dayChargeDto.isNoChargeOnWeekend());

                        dayChargeDto.setWeekendDiscountON(Boolean.TRUE);
                        //Add weekendDiscountInPercentage
                        dayChargeDto.setDailyRentalCharge((dayChargeDto.getDailyRentalCharge() * dayChargeDto.getWeekendDiscountInPercentage()) / 100);

                    } else {
                        ////System.out.println("STEP-7: noChargeStatusIsEnabled == FALSE ");

                        noChargeStatusIsEnabled = Boolean.FALSE;
                    }
                } else {
                    ////System.out.println("STEP-8: noChargeStatusIsEnabled == TRUE ");

                    noChargeStatusIsEnabled = Boolean.TRUE;
                }
                if (noChargeStatusIsEnabled) {
                    ////System.out.println("STEP-9: noChargeStatusIsEnabled == TRUE ");

                    dayChargeDto.getNonChargeableDateList().add(AppConstants.appDateFormatter.parse(checkOutDate));
                    dayChargeDto.setNoChargeDays(dayChargeDto.getNonChargeableDateList().size());
                } else {

                    dayChargeDto.getChargeableDateList().add(AppConstants.appDateFormatter.parse(checkOutDate));
                    dayChargeDto.setChargeDays(dayChargeDto.getChargeableDateList().size());
                    ////System.out.println("STEP-10: noChargeStatusIsEnabled == FALSE & No of Chargeable Days: "dayChargeDto.getChargeableDateList().size());

                }
                totalNoOfRentalDay--;
            } catch (ParseException e) {
                e.getStackTrace();
            }
            return extractIndividualRentalChargeableDates(checkOutDate, totalNoOfRentalDay, dayChargeDto);
        }
        return dayChargeDto;
    }


}
