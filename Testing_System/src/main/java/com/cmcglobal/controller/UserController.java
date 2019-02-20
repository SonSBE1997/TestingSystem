package com.cmcglobal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cmcglobal.service.ServiceResult;
import com.cmcglobal.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.cmcglobal.entity.User;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/users/{id}/semesters", method = RequestMethod.GET)
  public ResponseEntity<ServiceResult> getSemesterListByUserId(
      @PathVariable("id") String id) {
    return new ResponseEntity<ServiceResult>(
        userService.getSemseterListByUserId(id), HttpStatus.OK);
  }

  @RequestMapping(value = "/semesters/{semesterId}/tests/", method = RequestMethod.GET)
  public ResponseEntity<ServiceResult> getTestListOfUser(
      @PathVariable("semesterId") String semesterId) {
    return new ResponseEntity<ServiceResult>(
        userService.getExamBySemesterExamId(semesterId), HttpStatus.OK);
  }

  @GetMapping(value = "/user/listUsers")
  public List<User> listUsers() {
    /*
     * cate.delete(cate.getOne(1)); cate.deleteAll();
     */

    return userService.findAll();
  }

  @GetMapping(value = "/user/{id}")
  public User getUser(@PathVariable int id) {
    return userService.findByID(id);
  }
}
