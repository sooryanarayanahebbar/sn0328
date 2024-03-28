package com.tools.rental.util;

import com.tools.rental.AppConstants;
import com.tools.rental.order.OrderDto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ToolsRentalHelper {

    public static List<String> validateOrders(List<OrderDto> listDto){
        List<String> errors = new ArrayList<>();
for(OrderDto dto : listDto) {
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
        return errors;
    }


    /**
     *
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
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     *
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
        }catch (Exception ex) {
            ex.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     *
     * @param inputDateString |Ex: 'Sun May 26 00:00:00 IST 2024'
     * @return | Ex; 05/26/24
     */
    public static String appDateFormat(String inputDateString) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern( AppConstants.fullDateTimeFormatterPattern )
                .withLocale( Locale.US );
        ZonedDateTime zdt = ZonedDateTime.parse( inputDateString , f );


        LocalDate ld = zdt.toLocalDate();
        DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern( AppConstants.dateTimeFormatterPattern );
        String output = ld.format( fLocalDate) ;


        return output;
    }

    public static boolean isWeekDays(String dateStr) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern( AppConstants.fullDateTimeFormatterPattern )
                .withLocale( Locale.US );
        ZonedDateTime zdt = ZonedDateTime.parse( dateStr , f );


        LocalDate ld = zdt.toLocalDate();


        // LocalDate date = LocalDate.now();
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        boolean flag = true;
        switch (day) {
            case SATURDAY:
                System.out.println("Weekend - Saturday");
                flag = false;
            case SUNDAY:
                System.out.println("Weekend - Sunday");
                flag = false;
            default:
                System.out.println("Not a Weekend");
        }
        return flag;
    }
}
