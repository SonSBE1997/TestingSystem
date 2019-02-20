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

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users/{id}/semesters", method = RequestMethod.GET)
	public ResponseEntity<ServiceResult> getSemesterListByUserId(@PathVariable("id") String id) {
		return new ResponseEntity<ServiceResult>(userService.getSemseterListByUserId(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/semesters/{semesterId}/tests/", method = RequestMethod.GET)
	public ResponseEntity<ServiceResult> getTestListOfUser(@PathVariable("semesterId") String semesterId) {
		return new ResponseEntity<ServiceResult>(userService.getExamBySemesterExamId(semesterId), HttpStatus.OK);
	}
}
