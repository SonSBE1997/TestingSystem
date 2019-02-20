package com.cmcglobal.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.CategoryRepository;
import com.cmcglobal.service.ExamService;
import com.cmcglobal.utils.ExportExamPDF;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ExamController {
	@Autowired
	ExamService examService;
	@Autowired
	CategoryRepository categoryRepository;

	@PostMapping(value = "/create")
	public void postExam(@RequestBody Exam exam) {
		examService.createExam(exam);
	}

	@GetMapping(value = "/listExams")
	public List<Exam> listExam() {
		return examService.findAll();
	}

	@RequestMapping(value = "listExams/pagination", method = RequestMethod.GET)
	private List<Exam> getPageExam(
			@RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(name = "sortTerm", required = false, defaultValue = "title") String sortTerm) {
		Pageable sortedBy = null;
		Sort sortable = null;
		switch (sortTerm) {
		case ("title"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("title").ascending();
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("title").descending();
			}
			break;
		case ("category"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("category").ascending();
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("category").descending();
			}
			break;
		case ("examId"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("examId").ascending();
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("examId").descending();
			}
			break;
		case ("duration"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("duration").ascending();
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("duration").descending();
			}
			break;
		case ("numberOfQuestion"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("numberOfQuestion").ascending();
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("numberOfQuestion").descending();
			}
			break;
		case ("status"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("status").ascending();
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("status").descending();
			}
			break;
		case ("createAt"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("createAt").ascending();
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortable = Sort.by("createAt").descending();
			}
			break;
		// sort theo trường fullname của user create_by
		case ("userCreated"):
			if (("asc").equals(sortOrder.toLowerCase())) {
				sortedBy = PageRequest.of(page, size);
				return examService.pageExamSortByUserCreatedByAsc(sortedBy);
			}
			if (("desc").equals(sortOrder.toLowerCase())) {
				sortedBy = PageRequest.of(page, size);
				return examService.pageExamSortByUserCreatedByDesc(sortedBy);
			}
			break;
		}
		sortedBy = PageRequest.of(page, size, sortable);
		return examService.pageExam(sortedBy);
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
		examService.randomQuestion(exam.getExamId(), exam.getNumberOfQuestion());
		return ResponseEntity.ok("Ok");
	}
	
	@DeleteMapping(value = "/{examId}")
	public void deleteExam(@PathVariable String examId) {
		examService.deleteExam(examId);
	}
	@PostMapping(value = "/filter")
	public ResponseEntity<List<Exam>> findAll(@RequestBody Exam exam) {
		List<Exam> exams = examService.FilterExam(exam);
		return ResponseEntity.ok(exams);
	}

//	@GetMapping("/{examId}")
//	public Exam getOne(@PathVariable String examId) {
//		return examService.getOne(examId);
//	}

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
		List<Exam> list = examService.findAll();
		int x = list.size() + 1;
		for (Exam exam : listExam) {
			String id = "Exam" + String.valueOf(x);
			
			exam.setExamId(examService.createId());
			++x;
			User user = new User();
			user.setUserId(1);
			exam.setUserCreated(user);
			exam.setStatus("Draft");
			exam.setCreateAt(new Date());
			examService.insert(exam);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Ok");
	}
}

//package com.cmcglobal.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.cmcglobal.entity.Exam;
//import com.cmcglobal.repository.CategoryRepository;
//import com.cmcglobal.service.ExamService;
//import com.cmcglobal.utils.ExportExamPDF;
//
//@RestController
//@RequestMapping("/exam")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//public class ExamController {
//	@Autowired
//	ExamService examService;
//	@Autowired
//	CategoryRepository cate;
//
//	@PostMapping(value = "/create")
//	public void postExam(@RequestBody Exam exam) {
//		examService.createExam(exam);
//	}
//
//	@GetMapping(value = "/listExams")
//	public List<Exam> listExam() {
//		return examService.findAll();
//	}
//
//	@RequestMapping(value = "listExams/pagination", method = RequestMethod.GET)
//	private List<Exam> getPageExam(
//			@RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer page,
//			@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size,
//			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
//			@RequestParam(name = "sortTerm", required = false, defaultValue = "title") String sortTerm) {
//		Pageable sortedBy = null;
//		Sort sortable = null;
//		switch (sortTerm) {
//		case ("title"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("title").ascending();
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("title").descending();
//			}
//			break;
//		case ("category"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("category").ascending();
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("category").descending();
//			}
//			break;
//		case ("examId"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("examId").ascending();
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("examId").descending();
//			}
//			break;
//		case ("duration"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("duration").ascending();
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("duration").descending();
//			}
//			break;
//		case ("numberOfQuestion"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("numberOfQuestion").ascending();
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("numberOfQuestion").descending();
//			}
//			break;
//		case ("status"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("status").ascending();
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("status").descending();
//			}
//			break;
//		case ("createAt"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("createAt").ascending();
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortable = Sort.by("createAt").descending();
//			}
//			break;
//		// sort theo trường fullname của user create_by
//		case ("userCreated"):
//			if (("asc").equals(sortOrder.toLowerCase())) {
//				sortedBy = PageRequest.of(page, size);
//				return examService.pageExamSortByUserCreatedByAsc(sortedBy);
//			}
//			if (("desc").equals(sortOrder.toLowerCase())) {
//				sortedBy = PageRequest.of(page, size);
//				return examService.pageExamSortByUserCreatedByDesc(sortedBy);
//			}
//			break;
//		}
//		sortedBy = PageRequest.of(page, size, sortable);
//		return examService.pageExam(sortedBy);
//	}
//
//	@GetMapping(value = "/export/{id}")
//	public ModelAndView handlereport(@PathVariable("id") String id) {
//		try {
//			Exam exam = examService.findByID(id);
//			return new ModelAndView(new ExportExamPDF(), "exam", exam);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	@GetMapping(value = "/{id}")
//	public Exam getExam(@PathVariable("id") String id) {
//		/*
//		 * cate.delete(cate.getOne(1)); cate.deleteAll();
//		 */
//		return examService.findByID(id);
//	}
//
//	@PutMapping(value = "/approve")
//	public ResponseEntity<String> approveExam(@RequestBody Exam exam) {
//		boolean success = examService.approveExam(exam.getExamId());
//		if (success)
//			return ResponseEntity.ok("Ok");
//		return ResponseEntity.ok("Not ok");
//	}
//
//	@PutMapping(value = "/remove-question")
//	public ResponseEntity<String> removeQuestion(@RequestBody Exam exam) {
//		boolean success = examService.removeQuestion(exam);
//		if (success)
//			return ResponseEntity.ok("Ok");
//		return ResponseEntity.ok("Not ok");
//	}
//
//	@PostMapping(value = "/add-question")
//	public ResponseEntity<String> addQuestion(@RequestBody Exam exam) {
//		examService.addListQuestion(exam);
//		return ResponseEntity.ok("Ok");
//	}
//
//	@PostMapping(value = "/random-question")
//	public ResponseEntity<String> randomQuestion(@RequestBody Exam exam) {
//		examService.randomQuestion(exam.getExamId(), exam.getNumberOfQuestion());
//		return ResponseEntity.ok("Ok");
//	}
//
//	@DeleteMapping(value = "/{examId}")
//	public void deleteExam(@PathVariable String examId) {
//		examService.deleteExam(examId);
//	}
//	@PostMapping(value = "/filter")
//	public ResponseEntity<List<Exam>> findAll(@RequestBody Exam exam) {
//		List<Exam> exams = examService.FilterExam(exam);
//		return ResponseEntity.ok(exams);
//	}
//}
