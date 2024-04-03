package com.tools.rental.model.entity;

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

    private boolean isNoChargeOnWeekend;

    private boolean isNoChargeOnHoliday;

    public ToolBrandBean toolBrand;

}
