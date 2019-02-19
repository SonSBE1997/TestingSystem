package com.cmcglobal.service;

import java.util.List;

import com.cmcglobal.entity.Category;

/*
 * @author ptphuong.
 * Created date: Feb 15, 2019
 * Created time: 1:16:43 PM
 * Description: TODO - 
 */
public interface CategoryService {

    /**
     * Author: ptphuong.
     * Created date: Feb 15, 2019
     * Created time: 1:16:52 PM
     * Description: TODO - .
     * @return
     */
    List<Category> findAll();
    
    public List<Category> getAll();
	
	public Category getOneById(int categoryId);
	
	public Category getOne(String categoryName);
}
