/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.controller
 * File name: UserController.java
 * Author: ptphuong.
 * Created date: Feb 19, 2019
 * Created time: 2:45:23 PM
 */

package com.cmcglobal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.User;
import com.cmcglobal.service.UserService;

/*
 * @author ptphuong.
 * Created date: Feb 19, 2019
 * Created time: 2:45:23 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/listUsers")
    public List<User> listUsers() {
      /*
       * cate.delete(cate.getOne(1)); cate.deleteAll();
       */

      return userService.findAll();
    }
    int id=2;
    @GetMapping(value = "/")
    public User getUser() {
      /*
       * cate.delete(cate.getOne(1)); cate.deleteAll();
       */
      return userService.findByID(id);
    }
}
