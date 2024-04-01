package com.tools.rental.model.dto;

import com.tools.rental.admin.tool.ToolDetailsBean;
import com.tools.rental.order.OrderDto;
import com.tools.rental.order.RentalAgreementDto;
import com.tools.rental.util.DateUtils;
import com.tools.rental.util.ToolsRentalHelper;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Data
public class ToolRental {
    private RentalAgreementDto rentalAgreementDto = null;
    private String firstMondaySeptOfCheckoutYearsStrDate = "";
    private String independenceDayOfCheckoutYearsStrDate = "";

    private String dayOfTheWeekCheckOut = "";

    private boolean isWeekendCheckOut;

    @ToString.Include(name = "Tool Code")
    private String toolCode;

    @ToString.Include(name = "Tool Type")
    private String toolType;

    @ToString.Include(name = "Tool Brand")
    private String toolBrand;

    @ToString.Include(name = "Rental Days")
    private int rentalDays;

    @ToString.Include(name = "Checkout Date")
    private String checkOutDate;

    @ToString.Include(name = "Due Date")
    private String dueDate; //CheckoutDate+rentalDays //MM/dd/yy

    @ToString.Include(name = "Daily Rental Charge")
    private Double dailyRentalCharge; ////----1

    @ToString.Include(name = "Charge Days")
    private int chargeDays; //Count Chargeable Days , Include Due date, exclude 'No Change' days , (Holidays + weekend no charge tools+etc)

    @ToString.Include(name = "No Charge Days")
    private int noChargeDays;

    @ToString.Include(name = "Pre Discount Charge : %")
    private Double preDiscountCharge; // [chargeDays]  X  [daily Charges] (Round half upto cents)


    @ToString.Include(name = "Discount : %")
    private int discountPercent; //specified  at Checkout


    @ToString.Include(name = "Discount Amount : %")
    private Double discountAmount; //(([preDiscountCharge] X [discountPercent]) / 100) : Rounded half upto cents


    @ToString.Include(name = "Final Charge: $")
    private Double finalCharge; //[preDiscountCharge] - [discountAmount]

    private int weekendDiscountInPercentage;
    private int holidayDiscountInPercentage;
    private boolean isNoChargeOnWeekend;
    private boolean isNoChargeOnHoliday;
    private List<String> daysDailyChargeApplies;

    boolean isHolidayDiscountON; // If ON=TRUE then consider 'holidayDiscountInPercentage' while applying charge
    boolean isWeekendDiscountON; // If ON=TRUE then consider 'weekendDiscountInPercentage' while applying charge

    List<Date> chargeableDateList = new ArrayList<>();
    List<Date> nonChargeableDateList = new ArrayList<>();

    public ToolRental(ToolDetailsBean toolsDetails, OrderDto dto) {
        if (null == toolsDetails
                || null == toolsDetails.getToolCode()
                || null == toolsDetails.getToolType().getToolBrand()
        ) {
            return;
        }
        this.checkOutDate = dto.getCheckOutDate();
        this.daysDailyChargeApplies = Arrays.asList(toolsDetails.getToolType().getDaysDailyChargeApplies().split(","));
        this.discountPercent = dto.getDiscount();
        this.rentalDays = dto.getRentalDays();


        this.dayOfTheWeekCheckOut = DateUtils.getDayOfWeek(this.checkOutDate);

        this.isWeekendCheckOut = (("SUNDAY".equalsIgnoreCase(this.dayOfTheWeekCheckOut) || "SATURDAY".equalsIgnoreCase(this.dayOfTheWeekCheckOut)));////****

        this.toolCode = toolsDetails.getToolCode();
        this.toolType = null != toolsDetails.getToolType().getName()
                ? toolsDetails.getToolType().getName() : "";

        this.toolBrand = null != toolsDetails.getToolType().getToolBrand().getName()
                ? toolsDetails.getToolType().getToolBrand().getName() : "";


        this.dailyRentalCharge = null != toolsDetails.getToolType().getAmount()
                ? toolsDetails.getToolType().getAmount() : 0.0;

        this.weekendDiscountInPercentage = toolsDetails.getToolType().getWeekendDiscountInPercentage();
        this.holidayDiscountInPercentage = toolsDetails.getToolType().getHolidayDiscountInPercentage();
        this.isNoChargeOnWeekend = toolsDetails.getToolType().isNoChargeOnWeekend();
        this.isNoChargeOnHoliday = toolsDetails.getToolType().isNoChargeOnHoliday();

        //Find this.dueDate
        this.dueDate = ToolsRentalHelper.findDueDate(this.checkOutDate, this.rentalDays);

        /**
         * ChargeDays Calculation Section
         */
        //Find this.chargeDays
        DayChargeDto dayChargeDto = new DayChargeDto();
        dayChargeDto.setCheckOutDate(this.checkOutDate);
        dayChargeDto.setRentalDays(this.rentalDays);
        dayChargeDto.setDailyRentalCharge(this.dailyRentalCharge);
        dayChargeDto.setDaysDailyChargeApplies(this.daysDailyChargeApplies);
        dayChargeDto.setNoChargeOnHoliday(this.isNoChargeOnHoliday);
        dayChargeDto.setNoChargeOnWeekend(this.isNoChargeOnWeekend);
        dayChargeDto.setHolidayDiscountInPercentage(this.holidayDiscountInPercentage);
        dayChargeDto.setWeekendDiscountInPercentage(this.weekendDiscountInPercentage);

        dayChargeDto = ToolsRentalHelper.findChargeDays(dayChargeDto);

        this.chargeDays = dayChargeDto.getChargeDays();
        this.firstMondaySeptOfCheckoutYearsStrDate = dayChargeDto.getFirstMondaySeptOfCheckoutYearsStrDate();
        this.independenceDayOfCheckoutYearsStrDate = dayChargeDto.getIndependenceDayOfCheckoutYearsStrDate();
        this.noChargeDays = dayChargeDto.getNoChargeDays();
        this.chargeableDateList = dayChargeDto.getChargeableDateList();
        this.nonChargeableDateList = dayChargeDto.getNonChargeableDateList();

        /*  ChargeDays Calculation Section  Ends **/


        //Find this.preDiscountCharge
        this.preDiscountCharge = ToolsRentalHelper.findPreDiscountCharge(chargeDays, this.dailyRentalCharge);

        this.discountAmount = ToolsRentalHelper.findDiscountAmount(this.preDiscountCharge, this.discountPercent);

        this.finalCharge = ToolsRentalHelper.findFinalCharge(this.preDiscountCharge, this.discountAmount);

        //Show the Result - Rental Agreement

        rentalAgreementDto = new RentalAgreementDto(
                this.toolCode,
                toolsDetails.toolType.getName(),
                toolsDetails.toolType.getToolBrand().getName(),
                this.rentalDays,
                this.checkOutDate,
                this.dueDate,
                this.dailyRentalCharge,
                this.chargeDays,
                this.preDiscountCharge,
                this.discountPercent,
                this.discountAmount,
                String.valueOf(this.finalCharge)
        );

    }

    public String printRentalAgreement() {
        if (null != this.rentalAgreementDto) {
            return this.rentalAgreementDto.toString();
        }
        return "NO DATA!";
    }

}
