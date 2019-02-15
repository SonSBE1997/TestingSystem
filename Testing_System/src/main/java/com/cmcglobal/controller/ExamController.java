package com.cmcglobal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.repository.CategoryRepository;
import com.cmcglobal.service.ExamService;
import com.cmcglobal.utils.ExportExamPDF;

@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ExamController {
  @Autowired
  ExamService examService;

  @Autowired
  CategoryRepository cate;
  
  @PostMapping(value = "/create")
  public void postExam(@RequestBody Exam exam) {
	examService.createExam(exam);
  }

  @GetMapping(value = "/listExams")
  public List<Exam> listExam() {
    /*
     * cate.delete(cate.getOne(1)); cate.deleteAll();
     */

    return examService.findAll();
  }

  @GetMapping(value = "/export/{id}")
  public ModelAndView handlereport(@PathVariable("id") String id) {
    try {
      Exam exam = examService.findByID(id);
      return new ModelAndView(new ExportExamPDF(), "exam", exam);
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping(value = "/{id}")
  public Exam getExam(@PathVariable("id") String id) {
    /*
     * cate.delete(cate.getOne(1)); cate.deleteAll();
     */

    return examService.findByID(id);
  }

  @PutMapping(value = "/approve")
  public ResponseEntity<String> approveExam(@RequestBody Exam exam) {
    boolean success = examService.approveExam(exam.getExamId());
    if (success)
      return ResponseEntity.ok("Ok");
    return ResponseEntity.ok("Not ok");
  }

  @PutMapping(value = "/remove-question")
  public ResponseEntity<String> removeQuestion(@RequestBody Exam exam) {
    boolean success = examService.removeQuestion(exam);
    if (success)
      return ResponseEntity.ok("Ok");
    return ResponseEntity.ok("Not ok");
  }

  @PostMapping(value = "/add-question")
  public ResponseEntity<String> addQuestion(@RequestBody Exam exam) {
    examService.addListQuestion(exam);
    return ResponseEntity.ok("Ok");
  }

  @PostMapping(value = "/random-question")
  public ResponseEntity<String> randomQuestion(@RequestBody Exam exam) {
    examService.randomQuestion(exam.getExamId());
    return ResponseEntity.ok("Ok");
  }
}
