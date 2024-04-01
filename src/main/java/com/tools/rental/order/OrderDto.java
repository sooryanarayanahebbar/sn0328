package com.tools.rental.order;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto {


    private String toolCode;


    private String checkOutDate;

    private int rentalDays;

    private int discount;


}
