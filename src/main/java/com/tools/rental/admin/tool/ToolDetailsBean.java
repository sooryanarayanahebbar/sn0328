package com.tools.rental.admin.tool;

import com.tools.rental.admin.type.ToolTypeBean;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ToolDetailsBean {


    protected Integer id;


    private String toolCode;


    private String name;


    private String totalUnits;


    private String availableUnits;


    private boolean status;


    public ToolTypeBean toolType;

}
