package com.tools.rental.product;

import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tools.rental.admin.tool.ToolDetails;
import com.tools.rental.admin.tool.ToolRepository;
import com.tools.rental.admin.tool.ToolService;
import com.tools.rental.security.auth.AuthService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ToolService toolService;
	
	private final ToolRepository toolRepository;

	@Override
	public List<ProductDto> products(){
		List<ProductDto> products = new ArrayList<ProductDto>();
		
		List<ToolDetails> toolsDetails = toolService.toolDetails();
		
		
		return null;
	}
	

}
