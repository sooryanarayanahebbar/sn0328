package com.tools.rental.admin.tool;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ToolServiceImpl implements ToolService {

	ToolRepository toolRepository;

	public List<ToolDetails> toolDetails() {

		List<ToolDetails> entities = toolRepository.findAll();

		return entities;

	}

	@Override
	public ToolDetails addToolDetails(ToolDetails entity) {
		return this.toolRepository.save(entity);
	}

	@Override
	public ToolDetails toolDetailsByCode(String code) {

		Optional<ToolDetails> entity = this.toolRepository.findByCode(code);

		return entity.get();
	}

	@Override
	public ToolDetails updateToolDetails(String code, ToolDetails dto) {

		ToolDetails entity = this.toolRepository.findByCode(code).get();

		
		entity.setName(dto.getName());
		entity.setTotalUnits(dto.getTotalUnits());
		entity.setAvailableUnits(dto.getAvailableUnits());
		
		
		this.toolRepository.save(entity);

		return entity;
	}

}
