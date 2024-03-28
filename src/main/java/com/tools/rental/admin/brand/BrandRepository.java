package com.tools.rental.admin.brand;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface BrandRepository extends JpaRepository<ToolBrand, Integer> {
	
	@Query("select a from ToolBrand a where a.brandCode = ?1")
	Optional<ToolBrand> findByCode(String code);

}
