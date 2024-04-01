package com.tools.rental.admin.tool;

import java.util.List;


public interface ToolService {

    public List<ToolDetailsBean> toolDetails();


    public ToolDetailsBean toolDetailsByCode(String code);


}
