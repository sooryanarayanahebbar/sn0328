package com.tools.rental.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Data
public class OrderResponse {

    @ToString.Include(name = "Order Details: ")
    private List<ToolRental> rentalToolResponse = new ArrayList<>();


    @ToString.Include(name = "Grand Total PreDiscount")
    private double grandTotalPreDiscount;

    @ToString.Include(name = "Grand Total Discount")
    private double grandTotalDiscount;


    @ToString.Include(name = "Grand Total Of Final Charge")
    private double grandTotalFinalCharge; //[grandTotalPreDiscountAmount - grandTotalDiscountAmount]

    @ToString.Include(name = "Grand Total Charged Amount")
    private double grandTotalChargedAmount;

    List<String> orderList = new ArrayList<>();

    public OrderResponse(List<ToolRental> rentalToolResponse) {
        this.rentalToolResponse = rentalToolResponse;
        for (ToolRental res : rentalToolResponse) {
            orderList.add(res.toString());
           /* System.out.println("--------------------------------------------------------------");
            System.out.println(res.toString());
            System.out.println("**************************************************************");
*/
        }

    }


}
