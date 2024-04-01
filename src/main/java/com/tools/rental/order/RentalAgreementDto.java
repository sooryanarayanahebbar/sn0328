package com.tools.rental.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tools.rental.util.ToolsRentalHelper;
import lombok.Data;


@Data
public class RentalAgreementDto {

    public RentalAgreementDto(
            String toolCode,
            String typeName,
            String brandName,
            int rentalDays,
            String checkOutDate,
            String dueDate,
            Double dailyRentalCharge,
            int chargeDays,
            Double preDiscountCharge,
            int discountPercent,
            Double discountAmount,
            String finalCharge
    ) {
        this.toolCode = toolCode;
        this.typeName = typeName;
        this.brandName = brandName;
        this.rentalDays = rentalDays;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    @JsonProperty(value = "Tool Code")
    private String toolCode;

    @JsonProperty(value = "Tool Type")
    private String typeName;

    @JsonProperty(value = "Tool Brand")
    private String brandName;

    @JsonProperty(value = "Rental Days")
    private int rentalDays;

    @JsonProperty(value = "Checkout Date")
    private String checkOutDate;


    @JsonProperty(value = "Due Date")
    private String dueDate;

    @JsonProperty(value = "Daily Rental Charge")
    private Double dailyRentalCharge;

    @JsonProperty(value = "Charge Days")
    private int chargeDays;

    @JsonProperty(value = "Pre-discount Charge")
    private Double preDiscountCharge;


    @JsonProperty(value = "Discount Percentage")
    private int discountPercent;

    @JsonProperty(value = "Discount Amount")
    private Double discountAmount;

    @JsonProperty(value = "Final Charge")
    private String finalCharge;


    @Override
    public String toString() {
        return "----------------------------------------------------\n" +
                "   Tool Code---------------: " + toolCode + "\n" +
                "   Tool Type---------------: " + typeName + "\n" +
                "   Tool Brand--------------: " + brandName + "\n" +
                "   Rental Days-------------: " + rentalDays + "\n" +
                "   Checkout Date-----------: " + checkOutDate + "\n" +
                "   Due Date----------------: " + dueDate + "\n" +
                "   Daily Rental Charge-----: " + dailyRentalCharge + "\n" +
                "   Charge Days-------------: " + chargeDays + "\n" +
                "   Pre-discount Charge-----: " + ToolsRentalHelper.roundHalfUpAndUSCurrencyFormat(preDiscountCharge) + "\n" +
                "   Discount Percentage-----: " + discountPercent + "%" + "\n" +
                "   Discount Amount---------: " + ToolsRentalHelper.roundHalfUpAndUSCurrencyFormat(discountAmount) + "\n" +
                "   Final Charge------------: " + finalCharge + "\n" +
                "----------------------------------------------------\n";
    }


}
