/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.service.serviceImpl
 * File name: CategoryServiceImpl.java
 * Author: ptphuong.
 * Created date: Feb 15, 2019
 * Created time: 1:18:14 PM
 */

package com.cmcglobal.service.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Category;
import com.cmcglobal.repository.CategoryRepository;
import com.cmcglobal.service.CategoryService;

/*
 * @author ptphuong.
 * Created date: Feb 15, 2019
 * Created time: 1:18:14 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    
    @Override
    public List<Category> findAll(){
	return categoryRepository.findAll();
    }
}
