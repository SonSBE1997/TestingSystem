package com.cmcglobal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.service.ExamService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/exam")
public class examController {
	
	@Autowired
	ExamService examService;
	
	@GetMapping("/{examId}")
	public Exam getOne(@PathVariable String examId) {
		return examService.getOne(examId);
	}
	
	@PutMapping("/update/update-common/{examId}")
	public Exam updateCommon(@RequestBody Exam exam, @PathVariable String examId) {
		Exam ex = examService.getOne(examId);
		if(ex != null) {
			ex.setTitle(exam.getTitle());
			ex.setCategory(exam.getCategory());
			ex.setDuration(exam.getDuration());
			ex.setNumberOfQuestion(exam.getNumberOfQuestion());
			ex.setNote(exam.getNote());
			ex.setStatus(exam.getStatus());
			examService.update(ex);
			System.out.println(exam.getCategory().getCategoryName());
		}
		return ex;
	}
}
