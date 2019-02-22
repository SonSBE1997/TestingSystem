package com.cmcglobal.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.CategoryRepository;
import com.cmcglobal.service.ExamService;
import com.cmcglobal.utils.Api;
import com.cmcglobal.utils.ExportExamPDF;

/*
 * @author Sanero.
 * Created date: Feb 22, 2019
 * Created time: 2:16:44 PM
 * Description: TODO - exam controller.
 */
@RestController
@RequestMapping(Api.Exam.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ExamController {
  @Autowired
  ExamService examService;
  @Autowired
  CategoryRepository cate;

  /**
   * Author: ptphuong.
   * Created date: Feb 22, 2019
   * Created time: 2:16:32 PM
   * Description: TODO - create exam.
   * @param exam
   */
  @PostMapping(value = "/create")
  public void postExam(@RequestBody Exam exam) {
    examService.createExam(exam);
  }

  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 2:16:55 PM
   * Description: TODO - get all exam.
   * @return
   */
  @GetMapping(value = "/listExams")
  public List<Exam> listExam() {
    return examService.findAll();
  }

  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 2:17:15 PM
   * Description: TODO - get list exam by page.
   * @param sortOrder
   * @param sortTerm
   * @param searchContent
   * @return
   */
  @RequestMapping(value = "listExams/pagination", method = RequestMethod.GET)
  private List<Exam> getPageExam(
      @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
      @RequestParam(name = "sortTerm", required = false, defaultValue = "title") String sortTerm,
      @RequestParam(name = "searchContent", required = false, defaultValue = "") String searchContent) {
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
        return examService.pageExamSortByUserCreatedByAsc(searchContent);
      }
      if (("desc").equals(sortOrder.toLowerCase())) {
        return examService.pageExamSortByUserCreatedByDesc(searchContent);
      }
      break;
    // sort theo trường category của category category_name
    case ("category"):
      if (("asc").equals(sortOrder.toLowerCase())) {
        return examService.pageExamSortByCategoryAsc(searchContent);
      }
      if (("desc").equals(sortOrder.toLowerCase())) {
        return examService.pageExamSortByCategoryDesc(searchContent);
      }
      break;
    }
    return examService.pageExam(searchContent, sortable);
  }

  /**
   * Author: nthung.
   * Created date: Feb 22, 2019
   * Created time: 2:17:43 PM
   * Description: TODO - export exam to pdf.
   * @param id
   * @return
   */
  @GetMapping(value = "/export/{id}")
  public ModelAndView handlereport(@PathVariable("id") String id) {
    try {
      Exam exam = examService.findByID(id);
      return new ModelAndView(new ExportExamPDF(), "exam", exam);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Author: vvdong.
   * Created date: Feb 22, 2019
   * Created time: 2:18:08 PM
   * Description: TODO - get exam by id.
   * @param id
   * @return
   */
  @GetMapping(value = "/{id}")
  public Exam getExam(@PathVariable("id") String id) {
    /*
     * cate.delete(cate.getOne(1)); cate.deleteAll();
     */
    return examService.findByID(id);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:03:02 PM
   * Description: TODO - controller handle approve exam to public.
   * 
   * @param exam
   * @return
   */
  @PutMapping(value = Api.Exam.APPROVE)
  public ResponseEntity<String> approveExam(@RequestBody Exam exam) {
    boolean success = examService.approveExam(exam.getExamId());
    if (success)
      return ResponseEntity.ok(Api.Exam.OK);
    return ResponseEntity.ok(Api.Exam.NOT_OK);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:02:52 PM
   * Description: TODO - controller handle remove question to exam.
   * 
   * @param exam
   * @return
   */
  @PutMapping(value = Api.Exam.REMOVE_QUESTION)
  public ResponseEntity<String> removeQuestion(@RequestBody Exam exam) {
    boolean success = examService.removeQuestion(exam);
    if (success)
      return ResponseEntity.ok(Api.Exam.OK);
    return ResponseEntity.ok(Api.Exam.NOT_OK);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:02:45 PM
   * Description: TODO - controller handle add question to exam.
   * 
   * @param exam
   * @return
   */
  @PostMapping(value = Api.Exam.ADD_QUESTION)
  public ResponseEntity<String> addQuestion(@RequestBody Exam exam) {
    boolean success = examService.addListQuestion(exam);
    if (success)
      return ResponseEntity.ok(Api.Exam.OK);
    return ResponseEntity.ok(Api.Exam.NOT_OK);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:02:29 PM
   * Description: TODO - controller handle random question to exam.
   * 
   * @param exam
   * @return
   */
  @PostMapping(value = Api.Exam.RANDOM_QUESTION)
  public ResponseEntity<String> randomQuestion(@RequestBody Exam exam) {
    boolean success = examService.randomQuestion(exam.getExamId(),
        exam.getNumberOfQuestion());
    if (success)
      return ResponseEntity.ok(Api.Exam.OK);
    return ResponseEntity.ok(Api.Exam.NOT_OK);
  }

  /**
   * Author: ndvan.
   * Created date: Feb 22, 2019
   * Created time: 2:18:47 PM
   * Description: TODO - controller hand delete exam.
   * @param examId
   */
  @DeleteMapping(value = "/{examId}")
  public void deleteExam(@PathVariable String examId) {
    examService.deleteExam(examId);
  }

  /**
   * Author: ndvan.
   * Created date: Feb 22, 2019
   * Created time: 2:21:11 PM
   * Description: TODO - controller handle filter exam.
   * @param exam
   * @return
   */
  @PostMapping(value = "/filter")
  public ResponseEntity<List<Exam>> findAll(@RequestBody Exam exam) {
    List<Exam> exams = examService.FilterExam(exam);
    return ResponseEntity.ok(exams);
  }

  /**
   * Author: hai95.
   * Created date: Feb 22, 2019
   * Created time: 2:21:47 PM
   * Description: TODO - controller handle update common.
   * @param exam
   * @param examId
   * @return
   */
  @PutMapping("/update/update-common/{examId}")
  public Exam updateCommon(@RequestBody Exam exam,
      @PathVariable String examId) {
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

  /**
   * Author: hai95.
   * Created date: Feb 22, 2019
   * Created time: 2:22:29 PM
   * Description: TODO - import excel file.
   * @param multipartFile
   * @return
   */
  @PostMapping("/import-excel-file")
  public ResponseEntity<String> readExcelFile(
      @RequestParam("multipartFile") MultipartFile multipartFile) {

    File file = new File("files");
    String pathToSave = file.getAbsolutePath();
    System.out.println(pathToSave);

    File fileTranfer = new File(
        pathToSave + "/" + multipartFile.getOriginalFilename());
    try {
      multipartFile.transferTo(fileTranfer);
    } catch (IllegalStateException e) {
    } catch (IOException e) {
    }
    System.out.println(multipartFile.getOriginalFilename());
    List<Exam> listExam = new ArrayList<>();
    try {
      listExam = examService.readExcel(fileTranfer.toString());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.OK).body("not Ok");
    }
    if (listExam.size() == 0) {
      return ResponseEntity.status(HttpStatus.OK).body("not Ok");
    }
    for (Exam exam : listExam) {
      exam.setExamId(examService.createId1());
      User user = new User();
      user.setUserId(1);
      exam.setUserCreated(user);
      exam.setStatus("Draft");
      exam.setCreateAt(new Date());
      examService.insert(exam);
    }
    return ResponseEntity.status(HttpStatus.OK).body("Ok");
  }

  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 2:22:44 PM
   * Description: TODO - check empty question in exam.
   * @param examId
   * @return
   */
  @GetMapping(value = Api.Exam.IS_EMPTY_QUESTION)
  public ResponseEntity<String> isEmptyQuestion(@PathVariable String examId) {
    boolean success = examService.isEmptyQuestionOfExam(examId);
    System.out.println(examId);
    if (success)
      return ResponseEntity.ok(Api.Exam.OK);
    return ResponseEntity.ok(Api.Exam.NOT_OK);
  }
}
