package com.cmcglobal.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
		if (ex != null) {
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

	@PostMapping("/import-excel-file")
	public ResponseEntity<String> readExcelFile(@RequestParam("multipartFile") MultipartFile multipartFile) {

		File file = new File("files");
		String pathToSave = file.getAbsolutePath();
		System.out.println(pathToSave);

		File fileTranfer = new File(pathToSave + "/" + multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(fileTranfer);
		} catch (IllegalStateException e) {
		} catch (IOException e) {
		}
		System.out.println(multipartFile.getOriginalFilename());

		List<Exam> listExam = examService.readExcel(fileTranfer.toString());
		if (listExam.size() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body("not Ok");
		}
		for (Exam exam : listExam) {
			System.out.println(examService.createId());
			exam.setExamId(examService.createId());
			examService.insert(exam);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Ok");
	}
}
