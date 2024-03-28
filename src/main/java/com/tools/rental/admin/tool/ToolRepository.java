package com.tools.rental.admin.tool;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ToolRepository extends JpaRepository<ToolDetails, Integer> {
	
	@Query("select a from ToolDetails a where a.toolCode = ?1")
	Optional<ToolDetails> findByCode(String code);

}
