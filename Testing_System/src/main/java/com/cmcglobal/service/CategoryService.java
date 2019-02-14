package com.cmcglobal.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Category;
import com.cmcglobal.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	public Category getOne(String categoryName) {
		return categoryRepository.findByName(categoryName);
	}
}
