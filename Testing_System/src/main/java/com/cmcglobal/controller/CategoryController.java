///**
// * Project name: Testing_System
// * Package name: com.cmcglobal.controller
// * File name: CategoryController.java
// * Author: ptphuong.
// * Created date: Feb 15, 2019
// * Created time: 1:11:19 PM
// */
//
//package com.cmcglobal.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cmcglobal.entity.Category;
//import com.cmcglobal.service.CategoryService;
//
//@CrossOrigin(origins="http://localhost:4200")
//@RestController
//@RequestMapping("/category")
//public class CategoryController {
//	
//	@Autowired
//	CategoryService categoryService;
//	
//	@GetMapping("/{categoryName}")
//	public Category getOne(@PathVariable String categoryName) {
//		return categoryService.getOne(categoryName);
//	}
//	
//	@GetMapping("/list-category")
//	public List<Category> getAll() {
//		return categoryService.getAll();
//	}
//=======

package com.cmcglobal.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmcglobal.entity.Category;
import com.cmcglobal.service.CategoryService;

/*
 * @author ptphuong.
 * Created date: Feb 15, 2019
 * Created time: 1:11:19 PM
 * Description: TODO - 
 */

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/listCategories")
    public List<Category> listCategories() {
      /*
       * cate.delete(cate.getOne(1)); cate.deleteAll();
       */

      return categoryService.findAll();
    }
	
	@GetMapping("/{categoryName}")
	public Category getOne(@PathVariable String categoryName) {
		return categoryService.getOne(categoryName);
	}
	
	@GetMapping("/list-category")
	public List<Category> getAll() {
		return categoryService.getAll();
	}
}
