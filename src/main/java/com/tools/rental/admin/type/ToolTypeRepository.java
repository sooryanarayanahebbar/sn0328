package com.tools.rental.admin.type;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ToolTypeRepository extends JpaRepository<ToolType, Integer> {
	
	@Query("select a from ToolType a where a.typeCode = ?1")
	Optional<ToolType> findByCode(String code);

}
