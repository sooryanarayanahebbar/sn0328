package com.tools.rental.product;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProductDto> findAllAddress() {

		return this.productService.products();
	}
	
	

}

