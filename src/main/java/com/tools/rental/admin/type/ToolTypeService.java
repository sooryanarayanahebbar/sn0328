package com.tools.rental.admin.type;

import java.util.List;


public interface ToolTypeService {
	
	public List<ToolType> ToolTypeDetails();
	
	public ToolType addToolTypeDetails(ToolType dto);

	public ToolType ToolTypeDetailsByCode(String code);

	public ToolType updateToolTypeDetails(String code, ToolType dto);

}
