package com.tools.rental.admin.type;

import com.tools.rental.admin.brand.ToolBrandBean;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ToolTypeBean {


    protected Integer id;


    private String typeCode;


    private String name;


    private Double amount;

    private String daysDailyChargeApplies;

    private Integer weekendDiscountInPercentage;

    private Integer holidayDiscountInPercentage;

    private boolean isNoChargeOnWeekend;

    private boolean isNoChargeOnHoliday;

    public ToolBrandBean toolBrand;

}
