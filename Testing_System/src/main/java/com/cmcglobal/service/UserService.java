package com.cmcglobal.service;

import java.util.List;

import com.cmcglobal.entity.User;

public interface UserService {
    ServiceResult getSemseterListByUserId(String id);

    ServiceResult getExamBySemesterExamId(String id);

    /**
     * Author: ptphuong. Created date: Feb 19, 2019 Created time: 2:44:39 PM
     * Description: TODO - .
     * 
     * @return
     */
    public List<User> findAll();

    /**
     * Author: ptphuong. Created date: Feb 20, 2019 Created time: 5:04:26 AM
     * Description: TODO - .
     * 
     * @param id
     * @return
     */
    public User findByID(int id);
    
}
