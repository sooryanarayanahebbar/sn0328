package com.tools.rental.admin.tool;

import java.util.List;


public interface ToolService {
	
	public List<ToolDetails> toolDetails();
	
	public ToolDetails addToolDetails(ToolDetails dto);

	public ToolDetails toolDetailsByCode(String code);

	public ToolDetails updateToolDetails(String code, ToolDetails dto);

}
