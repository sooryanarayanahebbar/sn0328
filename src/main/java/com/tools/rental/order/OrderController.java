package com.tools.rental.order;

import com.tools.rental.admin.brand.ToolBrand;
import com.tools.rental.admin.tool.ToolDetails;
import com.tools.rental.admin.tool.ToolService;
import com.tools.rental.product.ProductDto;
import com.tools.rental.product.ProductService;
import com.tools.rental.util.ToolsRentalHelper;
import io.swagger.v3.core.util.Json;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/checkout")
public class OrderController {


	private final ProductService productService;
	private final ToolService toolService;


	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public String checkout(@RequestBody @Valid List<OrderDto> orderList) {

		List<String> errors = ToolsRentalHelper.validateOrders(orderList);

		if(errors.isEmpty()) {
		for(OrderDto dto : orderList) {
			ToolDetails toolsDetails = toolService.toolDetailsByCode(dto.getToolCode());

		}
			//System.out.println(Json.pretty(orderList).toString());
			//System.out.println(Json.pretty(orderList).toString());
			//System.out.println("*********************************************");
			//System.out.println(Json.pretty(toolsDetails).toString());
			return "SUCCESS";//this.productService.addToolBrand(dto);
		}

		return "TRUE";
	}

	
	

}

