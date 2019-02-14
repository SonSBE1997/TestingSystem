package com.cmcglobal.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
      if (exam.getExamQuestions().size() <1) {
        return  null;
      }
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
}
