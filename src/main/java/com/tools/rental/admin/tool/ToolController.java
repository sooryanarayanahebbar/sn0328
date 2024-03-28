package com.tools.rental.admin.tool;
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
@RequestMapping(value = "/api/v1/admin/tool")
public class ToolController {
	@Autowired
	private ToolService toolService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<ToolDetails> findAllAddress() {

		return this.toolService.toolDetails();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ToolDetails toolDetails(@RequestBody ToolDetails dto) {

		return this.toolService.addToolDetails(dto);
	}
	
	@GetMapping(value = "/{code}")
	@ResponseStatus(value = HttpStatus.OK)
	public ToolDetails findByCode(@PathVariable String code) {
		return this.toolService.toolDetailsByCode(code);
	}

	@PutMapping(value = "/{code}")
	@ResponseStatus(value = HttpStatus.OK)
	public ToolDetails updateUser(@PathVariable String code, @RequestBody ToolDetails dto) {

		return this.toolService.updateToolDetails(code, dto);
	}

}

