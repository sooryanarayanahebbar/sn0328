package com.tools.rental.admin.type;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ToolTypeServiceImpl implements ToolTypeService {

	ToolTypeRepository toolRepository;

	public List<ToolType> ToolTypeDetails() {

		List<ToolType> entities = toolRepository.findAll();

		return entities;

	}

	@Override
	public ToolType addToolTypeDetails(ToolType entity) {
		return this.toolRepository.save(entity);
	}

	@Override
	public ToolType ToolTypeDetailsByCode(String code) {

		Optional<ToolType> entity = this.toolRepository.findByCode(code);

		return entity.get();
	}

	@Override
	public ToolType updateToolTypeDetails(String code, ToolType dto) {

		ToolType entity = this.toolRepository.findByCode(code).get();
		entity.setName(dto.getName());
		entity.setAmount(dto.getAmount());
		entity.setWeekendDiscountInPercentage(dto.getWeekendDiscountInPercentage());
		entity.setHolidayDiscountInPercentage(dto.getHolidayDiscountInPercentage());

		this.toolRepository.save(entity);

		return entity;
	}

}
