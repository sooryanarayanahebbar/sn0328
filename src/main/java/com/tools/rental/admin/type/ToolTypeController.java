package com.tools.rental.admin.type;
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
@RequestMapping(value = "/api/v1/admin/type")
public class ToolTypeController {
	@Autowired
	private ToolTypeService toolTypeService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<ToolType> findAllAddress() {

		return this.toolTypeService.ToolTypeDetails();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ToolType toolDetails(@RequestBody ToolType dto) {

		return this.toolTypeService.addToolTypeDetails(dto);
	}
	
	@GetMapping(value = "/{code}")
	@ResponseStatus(value = HttpStatus.OK)
	public ToolType findByCode(@PathVariable String code) {
		return this.toolTypeService.ToolTypeDetailsByCode(code);
	}

	@PutMapping(value = "/{code}")
	@ResponseStatus(value = HttpStatus.OK)
	public ToolType updateUser(@PathVariable String code, @RequestBody ToolType dto) {

		return this.toolTypeService.updateToolTypeDetails(code, dto);
	}

}

