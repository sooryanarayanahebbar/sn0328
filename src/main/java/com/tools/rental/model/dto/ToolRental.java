package com.tools.rental.model.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.tools.rental.model.entity.ToolDetailsBean;
import com.tools.rental.model.entity.ToolTypeBean;
import com.tools.rental.util.DateUtils;
import com.tools.rental.util.ToolsRentalHelper;

import lombok.Data;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Data
public class ToolRental {
    
 
    private String toolCode;

    private String toolType;

    private String toolBrand;

    private int rentalDays;

    private String checkOutDate;

    private String dueDate; //MM/dd/yy

    private Double dailyRentalCharge; 

    private int chargeDays; 

    private int noChargeDays;

    private Double preDiscountCharge; 


    private int discountPercent;


    private Double discountAmount; 


    private Double finalCharge; 

    

    public ToolRental(ToolDetailsBean toolsDetails, OrderDto dto) {
        if (null == toolsDetails
                || null == toolsDetails.getToolCode()
                || null == toolsDetails.getToolType().getToolBrand()
        ) {
            return;
        }
        this.checkOutDate = dto.getCheckOutDate();
        this.discountPercent = dto.getDiscount();
        this.rentalDays = dto.getRentalDays();


  
        this.toolCode = toolsDetails.getToolCode();
        this.toolType = null != toolsDetails.getToolType().getName()
                ? toolsDetails.getToolType().getName() : "";

        this.toolBrand = null != toolsDetails.getToolType().getToolBrand().getName()
                ? toolsDetails.getToolType().getToolBrand().getName() : "";


        this.dailyRentalCharge = null != toolsDetails.getToolType().getAmount()
                ? toolsDetails.getToolType().getAmount() : 0.0;

 
        this.dueDate = ToolsRentalHelper.findDueDate(this.checkOutDate, this.rentalDays);

        
        this.chargeDays = ToolsRentalHelper.findChargeDays(this.getCheckOutDate(), this.getRentalDays(), toolsDetails.getToolType());

        this.noChargeDays = (rentalDays-chargeDays);
        
        this.preDiscountCharge = ToolsRentalHelper.findPreDiscountCharge(this.chargeDays, this.dailyRentalCharge);

        this.discountAmount = ToolsRentalHelper.findDiscountAmount(this.preDiscountCharge, this.discountPercent);

        this.finalCharge = ToolsRentalHelper.findFinalCharge(this.preDiscountCharge, this.discountAmount);


       
    }

    @Override
    public String toString() {
        return "----------------------------------------------------\n" +
                "   Tool Code---------------: " + this.toolCode + "\n" +
                "   Tool Type---------------: " + this.toolType + "\n" +
                "   Tool Brand--------------: " + this.toolBrand + "\n" +
                "   Rental Days-------------: " + this.rentalDays + "\n" +
                "   Checkout Date-----------: " + this.checkOutDate + "\n" +
                "   Due Date----------------: " + this.dueDate + "\n" +
                "   Daily Rental Charge-----: " + this.dailyRentalCharge + "\n" +
                "   Charge Days-------------: " + this.chargeDays + "\n" +
                "   Pre-discount Charge-----: " + ToolsRentalHelper.roundHalfUpAndUSCurrencyFormat(this.preDiscountCharge) + "\n" +
                "   Discount Percentage-----: " + this.discountPercent + "%" + "\n" +
                "   Discount Amount---------: " + ToolsRentalHelper.roundHalfUpAndUSCurrencyFormat(this.discountAmount) + "\n" +
                "   Final Charge------------: $" + this.finalCharge + "\n" +
                "----------------------------------------------------\n";
    }
    public String printRentalAgreement() {
            return this.toString();
       }

}
