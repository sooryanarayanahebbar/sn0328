package com.tools.rental.util;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tools.rental.config.AppConstants;
import com.tools.rental.config.OrderPayloadMessageProps;
import com.tools.rental.model.dto.OrderDto;
import com.tools.rental.model.dto.YearsHoliday;
import com.tools.rental.model.entity.ToolTypeBean;

@Component
public class ToolsRentalHelper {

	public static Map<Integer, YearsHoliday> yearlyHolidayCachedMap = new HashMap<>();
	public static String daysDailyChargeApplies = "";

	public static List<String> validateOrders(OrderDto dto, OrderPayloadMessageProps orderPayloadMessageProps) {
		List<OrderDto> listDto = new ArrayList<>();
		listDto.add(dto);
		return validateOrders(listDto, orderPayloadMessageProps);
	}

	public static List<String> validateOrders(List<OrderDto> listDto,
			OrderPayloadMessageProps orderPayloadMessageProps) {
		List<String> errors = new ArrayList<>();

		for (OrderDto dto : listDto) {
			if (null == dto) {
				errors.add(orderPayloadMessageProps.getEmptyData());
				return errors;
			}
			if (null == dto.getToolCode() || "".equals(dto.getToolCode().trim())) {
				errors.add(orderPayloadMessageProps.getEmptyToolCode());
			}

			if (dto.getRentalDays() < 1) {
				errors.add(orderPayloadMessageProps.getInvalidRentalDays());
			}
			if (dto.getRentalDays() > 999) {
				errors.add(orderPayloadMessageProps.getInvalidRentalDaysIsToobig());
			}

			if (dto.getDiscount() < 1 || dto.getDiscount() > 100) {
				errors.add(orderPayloadMessageProps.getInvalidDiscount());
			}

			if (null == dto.getCheckOutDate()) {
				errors.add(orderPayloadMessageProps.getInvalidCheckoutDate());
			}

			if (!isValidDate(dto.getCheckOutDate())) {
				errors.add(orderPayloadMessageProps.getInvalidCheckoutDateFormat());
			}
		}
		return errors;
	}

	/**
	 * @param appDateString | Ex; 05/26/24
	 * @return | Boolean
	 */
	public static boolean isValidDate(String appDateString) {
		if (null == appDateString || appDateString.split("/").length != 3) {
			return false;
		}
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

		DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
		boolean flag = true;
		switch (day) {
		case SATURDAY:
			flag = false;
		case SUNDAY:
			flag = false;
		default:
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
			c.add(Calendar.DATE, rentalDays - 1); // Adding days : -1?? , Including Checkout Date and Total rentalDays
			dueDate = AppConstants.appDateFormatter.format(c.getTime());
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
		/*
		 * Note: (([preDiscountCharge] X [discountPercent]) / 100) : Rounded half upto
		 * cents
		 */
		return (Double.valueOf((preDiscountCharge * discountPercent) / 100));
	}

	public static Double findFinalCharge(Double preDiscountCharge, Double discountAmount) {
		/* Note: [preDiscountCharge] - [discountAmount] */
		return (Double.valueOf((preDiscountCharge - discountAmount)));
	}

	@SuppressWarnings("deprecation")
	public static String roundHalfUpAndUSCurrencyFormat(Double amount) {
		BigDecimal result = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(result.doubleValue());
	}

	/* Find Charge Days Calculations */

	public static int findChargeDays(String checkoutDate, int rentDays, ToolTypeBean toolType) {
		int rentalDays = rentDays;
		daysDailyChargeApplies = (null != toolType.getDaysDailyChargeApplies() ? toolType.getDaysDailyChargeApplies()
				: "");
		int fullYear = Year.now().getValue();
		String[] ckoutDt = checkoutDate.split("/");
		if (null != ckoutDt && ckoutDt.length == 3) {
			fullYear = new BigDecimal(String.valueOf(fullYear).substring(0, 2) + "" + ckoutDt[2]).intValue();
		}
		YearsHoliday yearlyHoliday = yearlyHolidayCachedMap.get(fullYear);
		if (null == yearlyHoliday) {
			ToolsRentalHelper.getYearlyINDandFirstSeptMondayHolidays(fullYear, yearlyHolidayCachedMap);
			yearlyHoliday = yearlyHolidayCachedMap.get(fullYear);
		}
		String firstMonday = yearlyHoliday.getFirstMondaySeptStrDate();
		String independenceDay = yearlyHoliday.getIndependenceDayStrDate();

		int chargeDays = extractIndividualRentalChargeableDates(checkoutDate, rentalDays, firstMonday, independenceDay,
				toolType, 0);

		return chargeDays;
	}

	private static int extractIndividualRentalChargeableDates(String checkOutDate, int rentalDays, String firstMonday,
			String independenceDay, ToolTypeBean toolType, int days) {
		String today = ToolsRentalHelper.getDayOfWeek(checkOutDate);
		boolean isWeekendToday = (("SUNDAY".equalsIgnoreCase(today) || "SATURDAY".equalsIgnoreCase(today)));

		if (rentalDays > 0) {

			try {
				Date newDate = AppConstants.appDateFormatter.parse(checkOutDate);

				Calendar c = Calendar.getInstance();
				c.setTime(newDate);
				c.add(Calendar.DATE, 1); // +1
				checkOutDate = AppConstants.appDateFormatter.format(c.getTime());

				boolean noChargeStatusIsEnabled = Boolean.FALSE;

				if (daysDailyChargeApplies.indexOf(today) >= 0) {
					boolean indDayFlag = ToolsRentalHelper.isEquals(checkOutDate, independenceDay);
					boolean septMondayFlag = ToolsRentalHelper.isEquals(checkOutDate, firstMonday);

					if ((indDayFlag || septMondayFlag) && toolType.isNoChargeOnHoliday()) {
						noChargeStatusIsEnabled = Boolean.TRUE;

					} else if (isWeekendToday && toolType.isNoChargeOnWeekend()) {

						noChargeStatusIsEnabled = Boolean.TRUE;
					} else {

						noChargeStatusIsEnabled = Boolean.FALSE;
					}
				} else {

					noChargeStatusIsEnabled = Boolean.TRUE;
				}
				if (!noChargeStatusIsEnabled) {

					days = days + 1;

				}
				rentalDays--;

			} catch (ParseException e) {
				e.getStackTrace();
			}
			return extractIndividualRentalChargeableDates(checkOutDate, rentalDays, firstMonday, independenceDay,
					toolType, days);
		}
		return days;
	}
/////////




	public static void getYearlyINDandFirstSeptMondayHolidays(int fullYear, Map<Integer, YearsHoliday> yearlyHolidayMap) {
		YearsHoliday yearsHoliday = yearlyHolidayMap.get(fullYear);
		if (null == yearsHoliday) {
			yearsHoliday = new YearsHoliday();
		} else {
			return;
		}

		int currentYear = fullYear % 100;
		LocalDate date = LocalDate.of(currentYear, 7, 4);
		for (int i = 0; i < date.lengthOfMonth(); i++) {
			try {
				if ("SUNDAY".equalsIgnoreCase(date.getDayOfWeek().toString())) {
					Date newDate = AppConstants.appDateYFormatter.parse("7/05/" + currentYear);
					yearsHoliday.setIndependenceDayStrDate(convertDateToAppStrDate(newDate, 1));
					yearsHoliday.setIndDayOfWeek("SUNDAY");
					break;
				} else if ("SATURDAY".equalsIgnoreCase(date.getDayOfWeek().toString())) {
					Date newDate = AppConstants.appDateYFormatter.parse("7/03/" + currentYear);
					yearsHoliday.setIndependenceDayStrDate(convertDateToAppStrDate(newDate, -1));
					yearsHoliday.setIndDayOfWeek("SATURDAY");
					break;
				} else {
					Date newDate = AppConstants.appDateYFormatter.parse("7/04/" + currentYear);
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
		StringBuffer sb = new StringBuffer("09/");
		sb.append(firstSeptMondayDdate.getDayOfMonth());
		sb.append("/");
		sb.append(currentYear);

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
		String strDate = AppConstants.appDateYFormatter.format(c.getTime());
		return strDate;
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





	public static boolean isEquals(String strDate1, String strDate2) {
		Date newDate1 = null;
		Date newDate2 = null;
		try {
			newDate1 = AppConstants.appDateYFormatter.parse(strDate1);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(newDate1);

			newDate2 = AppConstants.appDateYFormatter.parse(strDate2);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(newDate2);

			return c1.equals(c2);

		} catch (ParseException e) {
			e.getStackTrace();
		}
		return false;
	}


}
