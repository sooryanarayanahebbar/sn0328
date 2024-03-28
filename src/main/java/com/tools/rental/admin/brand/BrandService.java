package com.tools.rental.admin.brand;

import java.util.List;


public interface BrandService {
	
	public List<ToolBrand> toolBrandDetails();
	
	public ToolBrand addToolBrand(ToolBrand dto);

	public ToolBrand toolBrandByCode(String code);

	public ToolBrand updateToolBrand(String code, ToolBrand dto);

}
