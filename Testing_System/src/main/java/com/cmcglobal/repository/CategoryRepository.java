package com.cmcglobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cmcglobal.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("select c from Category c where c.categoryName = :categoryName")
	public Category findByName(@Param("categoryName") String categoryName);
}
