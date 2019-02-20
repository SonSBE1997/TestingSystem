/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.service.serviceImpl
 * File name: UserServiceImpl.java
 * Author: ptphuong.
 * Created date: Feb 19, 2019
 * Created time: 2:39:47 PM
 */

package com.cmcglobal.service.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.UserRepository;
import com.cmcglobal.service.UserService;

/*
 * @author ptphuong.
 * Created date: Feb 19, 2019
 * Created time: 2:39:47 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
   UserRepository userRepository;
    
    @Override
    public List<User> findAll(){
	return userRepository.findAll();
    }
    
    @Override
    public User findByID(int id) {
      return userRepository.findById(id).get();
    }
}
