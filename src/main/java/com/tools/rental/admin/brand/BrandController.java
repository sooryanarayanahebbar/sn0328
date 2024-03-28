package com.tools.rental.admin.brand;
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
@RequestMapping(value = "/api/v1/admin/brand")
public class BrandController {
	@Autowired
	private BrandService toolService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<ToolBrand> findAllAddress() {

		return this.toolService.toolBrandDetails();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ToolBrand toolDetails(@RequestBody ToolBrand dto) {

		return this.toolService.addToolBrand(dto);
	}
	
	@GetMapping(value = "/{code}")
	@ResponseStatus(value = HttpStatus.OK)
	public ToolBrand findByCode(@PathVariable String code) {
		return this.toolService.toolBrandByCode(code);
	}

	@PutMapping(value = "/{code}")
	@ResponseStatus(value = HttpStatus.OK)
	public ToolBrand updateUser(@PathVariable String code, @RequestBody ToolBrand dto) {
		return this.toolService.updateToolBrand(code, dto);
	}

}

