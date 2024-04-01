package com.tools.rental.util;


import com.tools.rental.model.dto.YearsHoliday;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;

public class DateUtils {

    static String fullDateTimeFormatterPattern = "E MMM dd HH:mm:ss z uuuu";
    static SimpleDateFormat appDateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(fullDateTimeFormatterPattern).withLocale(Locale.US);

    public static void getYearlyINDandFirstSeptMondayHolidays(int fullYear, Map<Integer, YearsHoliday> yearlyHolidayMap) {
        YearsHoliday yearsHoliday = yearlyHolidayMap.get(fullYear);
        if (null == yearsHoliday) {
            yearsHoliday = new YearsHoliday();
        } else {
            return;
        }

        //IND DAY ;
        int currentYear = fullYear % 100;
        LocalDate date = LocalDate.of(currentYear, 7, 4);
        for (int i = 0; i < date.lengthOfMonth(); i++) {
            try {
                if ("SUNDAY".equalsIgnoreCase(date.getDayOfWeek().toString())) {
                    Date newDate = appDateFormatter.parse("7/05/" + currentYear);
                    yearsHoliday.setIndependenceDayStrDate(convertDateToAppStrDate(newDate, 1));
                    yearsHoliday.setIndDayOfWeek("SUNDAY");
                    break;
                } else if ("SATURDAY".equalsIgnoreCase(date.getDayOfWeek().toString())) {
                    Date newDate = appDateFormatter.parse("7/03/" + currentYear);
                    yearsHoliday.setIndependenceDayStrDate(convertDateToAppStrDate(newDate, -1));
                    yearsHoliday.setIndDayOfWeek("SATURDAY");
                    break;
                } else {
                    Date newDate = appDateFormatter.parse("7/04/" + currentYear);
                    yearsHoliday.setIndependenceDayStrDate(convertDateToAppStrDate(newDate, 0));
                    yearsHoliday.setIndDayOfWeek("WEEKEND");

                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        //1st Sept Monday

        LocalDate firstSeptMondayDdate = LocalDate.of(currentYear, 9, 1);
        for (int i = 0; i < firstSeptMondayDdate.lengthOfMonth(); i++) {
            if ("Monday".equalsIgnoreCase(firstSeptMondayDdate.getDayOfWeek().toString())) {

                break;
            } else {
                firstSeptMondayDdate = firstSeptMondayDdate.plusDays(1);
            }
        }
        ////System.out.println("DAY: " + firstSeptMondayDdate.getDayOfMonth());

        StringBuffer sb = new StringBuffer("09/");
        sb.append(firstSeptMondayDdate.getDayOfMonth());
        sb.append("/");
        sb.append(currentYear);
        //// System.out.println("DATE-SEPT: " + sb.toString());

        yearsHoliday.setFirstMondaySeptStrDate(sb.toString());

        /* Update back to repo */
        yearlyHolidayMap.put(fullYear, yearsHoliday);
    }

    public static String convertDateToAppStrDate(Date newDate, int increment) {
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        if (increment != 0) {
            c.add(Calendar.DATE, increment); // +1 or -1 or 0
        }
        String strDate = appDateFormatter.format(c.getTime());
        //System.out.println("out: " + strDate);
        return strDate;
    }


    ////////////////////////////////////////////////////////////////
    @Deprecated
    public static String[] getYearlyINDandFirstSeptMondayHolidays(Map<Integer, YearsHoliday> yearlyHolidayMap) {
        String[] currentYearIndependenveStrDateOrNear = {"", ""};
        int fullYear = Year.now().getValue();
        int currentYear = fullYear % 100;
        LocalDate date = LocalDate.of(currentYear, 7, 4);
        for (int i = 0; i < date.lengthOfMonth(); i++) {
            try {
                if ("SUNDAY".equalsIgnoreCase(date.getDayOfWeek().toString())) {
                    Date newDate = appDateFormatter.parse("7/05/" + currentYear);
                    currentYearIndependenveStrDateOrNear[0] = convertDateToAppStrDate(newDate, 1);
                    currentYearIndependenveStrDateOrNear[1] = "SUNDAY";
                    break;
                } else if ("SATURDAY".equalsIgnoreCase(date.getDayOfWeek().toString())) {
                    Date newDate = appDateFormatter.parse("7/03/" + currentYear);
                    currentYearIndependenveStrDateOrNear[0] = convertDateToAppStrDate(newDate, -1);
                    currentYearIndependenveStrDateOrNear[1] = "SATURDAY";
                    break;
                } else {
                    Date newDate = appDateFormatter.parse("7/04/" + currentYear);
                    currentYearIndependenveStrDateOrNear[0] = convertDateToAppStrDate(newDate, 0);
                    currentYearIndependenveStrDateOrNear[1] = "WEEKEND";

                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("currentYearIndependenveStrDateOrNear = " + currentYearIndependenveStrDateOrNear);

        return currentYearIndependenveStrDateOrNear;
    }


    public static String currentYearFirstMondayOfSeptember(Map<Integer, YearsHoliday> yearlyHolidayMap) {
        int currentYear = Year.now().getValue() % 100;
        LocalDate date = LocalDate.of(currentYear, 9, 1);
        for (int i = 0; i < date.lengthOfMonth(); i++) {
            if ("Monday".equalsIgnoreCase(date.getDayOfWeek().toString())) {

                break;
            } else {
                date = date.plusDays(1);
            }
        }
        System.out.println("DAY: " + date.getDayOfMonth());

        StringBuffer sb = new StringBuffer("09/");
        sb.append(date.getDayOfMonth());
        sb.append("/");
        sb.append(currentYear);
        ////System.out.println("DATE-SEPT: " + sb.toString());

        return sb.toString();
    }

    public static String getDayOfWeek(String appStrDate) {
        if (null == appStrDate || appStrDate.split("/").length != 3) {
            return "";
        }
        String[] arrDt = appStrDate.split("/");
        Integer year = new BigDecimal(arrDt[2]).intValue();
        Integer month = new BigDecimal(arrDt[0]).intValue();
        Integer day = new BigDecimal(arrDt[1]).intValue();
        LocalDate lDate = LocalDate.of(year, month, day);
        DayOfWeek d = DayOfWeek.of(lDate.get(ChronoField.DAY_OF_WEEK));
        return d.toString();
    }

    public static boolean isEquals(Date date1, String strDate2) {
        Date newDate2 = null;
        try {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date1);

            newDate2 = appDateFormatter.parse(strDate2);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(newDate2);

            return c1.equals(c2);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public static boolean isEquals(String strDate1, Date date2) {
        Date newDate2 = null;
        try {
            newDate2 = appDateFormatter.parse(strDate1);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(newDate2);

            Calendar c2 = Calendar.getInstance();
            c2.setTime(date2);

            return c1.equals(c2);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public static boolean isEquals(Date date1, Date date2) {
        try {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date1);

            Calendar c2 = Calendar.getInstance();
            c2.setTime(date2);

            return c1.equals(c2);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public static boolean isEquals(String strDate1, String strDate2) {
        Date newDate1 = null;
        Date newDate2 = null;
        try {
            newDate1 = appDateFormatter.parse(strDate1);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(newDate1);

            newDate2 = appDateFormatter.parse(strDate2);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(newDate2);

            return c1.equals(c2);

        } catch (ParseException e) {
            e.getStackTrace();
        }
        return false;
    }

    public static String decrementAsString(String strDate) {
        Date newDate = null;
        try {
            newDate = appDateFormatter.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(newDate);
            c.add(Calendar.DATE, -1); // +1
            strDate = appDateFormatter.format(c.getTime());
            // System.out.println("out: " + strDate);
            return strDate;
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static String incrementAsString(String strDate) {
        Date newDate = null;
        try {
            newDate = appDateFormatter.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(newDate);
            c.add(Calendar.DATE, 1); // +1
            strDate = appDateFormatter.format(c.getTime());
            return strDate;
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static Date incrementAsDate(Date date) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1); // +1
            String strDate = appDateFormatter.format(c.getTime());
            return appDateFormatter.parse(strDate);
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static Date decrementAsDate(Date date) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, -1); // +1
            String strDate = appDateFormatter.format(c.getTime());
            return appDateFormatter.parse(strDate);
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static Date incrementAsDate(String strDate) {
        Date newDate = null;
        try {
            newDate = appDateFormatter.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(newDate);
            c.add(Calendar.DATE, 1); // +1
            strDate = appDateFormatter.format(c.getTime());
            return appDateFormatter.parse(strDate);
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static Date decrementAsDate(String strDate) {
        Date newDate = null;
        try {
            newDate = appDateFormatter.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(newDate);
            c.add(Calendar.DATE, -1); // -1
            strDate = appDateFormatter.format(c.getTime());
            newDate = appDateFormatter.parse(strDate);
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return newDate;
    }

    public static Date asDate(String strDate) {
        Date newDate = null;
        try {
            newDate = appDateFormatter.parse(strDate);
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return newDate;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    //Format: 0023-01-31 ('02/02/23')
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
