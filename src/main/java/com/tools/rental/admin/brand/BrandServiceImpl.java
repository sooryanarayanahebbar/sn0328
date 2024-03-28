package com.tools.rental.admin.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

	BrandRepository toolRepository;

	public List<ToolBrand> toolBrandDetails() {

		List<ToolBrand> entities = toolRepository.findAll();

		return entities;

	}

	@Override
	public ToolBrand addToolBrand(ToolBrand entity) {
		return this.toolRepository.save(entity);
	}

	@Override
	public ToolBrand toolBrandByCode(String code) {

		Optional<ToolBrand> entity = this.toolRepository.findByCode(code);

		return entity.get();
	}

	@Override
	public ToolBrand updateToolBrand(String code, ToolBrand dto) {

		ToolBrand entity = this.toolRepository.findByCode(code).get();

		
		entity.setName(dto.getName());
		entity.setDescriptions(dto.getDescriptions());
		
		this.toolRepository.save(entity);

		return entity;
	}

}
