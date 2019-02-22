package com.cmcglobal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmcglobal.entity.Test;
import com.cmcglobal.service.ServiceResult;
import com.cmcglobal.service.TestService;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TestController {

    @Autowired
    TestService testService;

    /**
     * Create by: VuThuy - CMC
      Create date: Feb 19, 2019
      Modifier: VuThuy
      Modified date: Feb 19, 2019
      Description: List table test.
      Version 1.0
      @return
     */
    @GetMapping(value = "/listTest")
    public List<Test> listTest() {
        return testService.findAll();
    }

    
    /**
     * Create by: VuThuy - CMC
      Create date: Feb 19, 2019
      Modifier: VuThuy
      Modified date: Feb 19, 2019
      Description: listBySemester in table test
      Version 1.0
      @param id
      @return
     */
    @GetMapping(value = "/listBySemester/{id}")
    public List<Test> listBySemester(@PathVariable("id") String id) {
        return testService.findBySemesterID(id);
    }

    /**
     * Create by: VuThuy - CMC
      Create date: Feb 19, 2019
      Modifier: VuThuy
      Modified date: Feb 19, 2019
      Description: ....
      Version 1.0
      @param test
      @return
     */
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResult> insertTest(@RequestBody Test test) {
        return new ResponseEntity<ServiceResult>(testService.insertTest(test), HttpStatus.OK);
    }
    
    /**
     * Create by: VuThuy - CMC
      Create date: Feb 19, 2019
      Modifier: VuThuy
      Modified date: Feb 19, 2019
      Description: ....
      Version 1.0
      @param id
      @return
     */
    @PostMapping("/delete")
    public ResponseEntity<ServiceResult> deleteTest(@RequestBody Integer id) {
        return new ResponseEntity<ServiceResult>(testService.deleteTest(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/users/{userId}/semesters/{semesterId}/tests/", method=RequestMethod.GET)
    public ResponseEntity<ServiceResult> getTestsDetail(@PathVariable("userId") String userId,
        @PathVariable("semesterId") String semesterId) {
      ServiceResult result = new ServiceResult();
      result.setData(testService.getTestDetail(userId, semesterId));
      return new ResponseEntity<ServiceResult>(result, HttpStatus.OK);
    }
}