package com.cmcglobal.controller;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.repository.CategoryRepository;
import com.cmcglobal.service.ExamService;
import com.cmcglobal.utils.Api;
import com.cmcglobal.utils.Constants;
import com.cmcglobal.utils.ExportExamPdf;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
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

/*
 * @author Sanero.
 * Created date: Feb 22, 2019
 * Created time: 2:16:44 PM
 * Description: TODO - exam controller.
 */
@RestController
@RequestMapping(Api.Exam.BASE_URL)
@CrossOrigin(origins = Api.BASE_URL_CORS, maxAge = 3600)
public class ExamController {
  static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

  @Autowired
  ExamService examService;
  @Autowired
  CategoryRepository cate;

  /**
   * Author: ptphuong.
   * Created date: Feb 22, 2019
   * Created time: 2:16:32 PM
   * Description: TODO - create exam.
   * @param exam - exam.
   */
  @PostMapping(value = Api.Exam.CREATE)
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
  @GetMapping(value = Api.Exam.GET_ALL)
  public List<Exam> listExam() {
    return examService.findAll();
  }

  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 2:17:15 PM
   * Description: TODO - get list exam by page.
   * @param sortOrder - sort order.
   * @param sortTerm - sort term.
   * @param searchContent - search content.
   * @return
   */
  @RequestMapping(value = Api.Exam.GET_BY_PAGE, method = RequestMethod.GET)
  private List<Exam> getPageExam(
      @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
      @RequestParam(name = "sortTerm", required = false, defaultValue = "title") String sortTerm,
      @RequestParam(name = "searchContent", required = false, defaultValue = "")
      String searchContent) {
    return examService.getListExamByPage(sortOrder, sortTerm, searchContent);
  }

  /**
   * Author: nthung.
   * Created date: Feb 22, 2019
   * Created time: 2:17:43 PM
   * Description: TODO - export exam to pdf.
   * @param id - exam id.
   * @return
   */
  @GetMapping(value = Api.Exam.EXPORT)
  public ModelAndView handlereport(@PathVariable("id") String id) {
    try {
      Exam exam = examService.findById(id);
      return new ModelAndView(new ExportExamPdf(), "exam", exam);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Author: vvdong.
   * Created date: Feb 22, 2019
   * Created time: 2:18:08 PM
   * Description: TODO - get exam by id.
   * @param id - exam id.
   * @return
   */
  @GetMapping(value = Api.Exam.GET_ONE)
  public Exam getExam(@PathVariable String id) {
    /*
     * cate.delete(cate.getOne(1)); cate.deleteAll();
     */
    return examService.findById(id);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:03:02 PM
   * Description: TODO - controller handle approve exam to public.
   * 
   * @param exam - exam.
   * @return
   */
  @PutMapping(value = Api.Exam.APPROVE)
  public ResponseEntity<String> approveExam(@RequestBody Exam exam) {
    boolean success = examService.approveExam(exam.getExamId());
    if (success) {
      return ResponseEntity.ok(Constants.Exam.OK);
    }
    return ResponseEntity.ok(Constants.Exam.NOT_OK);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:02:52 PM
   * Description: TODO - controller handle remove question to exam.
   * 
   * @param exam - exam.
   * @return
   */
  @PutMapping(value = Api.Exam.REMOVE_QUESTION)
  public ResponseEntity<String> removeQuestion(@RequestBody Exam exam) {
    boolean success = examService.removeQuestion(exam);
    if (success) {
      return ResponseEntity.ok(Constants.Exam.OK);
    }
    return ResponseEntity.ok(Constants.Exam.NOT_OK);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:02:45 PM
   * Description: TODO - controller handle add question to exam.
   * 
   * @param exam - exam.
   * @return
   */
  @PostMapping(value = Api.Exam.ADD_QUESTION)
  public ResponseEntity<String> addQuestion(@RequestBody Exam exam) {
    boolean success = examService.addListQuestion(exam);
    if (success) {
      return ResponseEntity.ok(Constants.Exam.OK);
    }
    return ResponseEntity.ok(Constants.Exam.NOT_OK);
  }

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:02:29 PM
   * Description: TODO - controller handle random question to exam.
   * 
   * @param exam - exam.
   * @return
   */
  @PostMapping(value = Api.Exam.RANDOM_QUESTION)
  public ResponseEntity<String> randomQuestion(@RequestBody Exam exam) {
    boolean success = examService.randomQuestion(exam.getExamId(),
        exam.getCategory().getCategoryId(), exam.getNumberOfQuestion());
    if (success) {
      return ResponseEntity.ok(Constants.Exam.OK);
    }
    return ResponseEntity.ok(Constants.Exam.NOT_OK);
  }

  /**
   * Author: ndvan.
   * Created date: Feb 22, 2019
   * Created time: 2:18:47 PM
   * Description: TODO - controller hand delete exam.
   * @param examId - examId.
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
   * @param exam - exam.
   * @return
   */
  @PostMapping(value = Api.Exam.FILTER)
  public ResponseEntity<List<Exam>> findAll(@RequestBody Exam exam) {
    List<Exam> exams = examService.filterExam(exam);
    return ResponseEntity.ok(exams);
  }

  /**
   * Author: hai95.
   * Created date: Feb 22, 2019
   * Created time: 2:21:47 PM
   * Description: TODO - controller handle update common.
   * @param exam - exam.
   * @param examId - exam id.
   * @return
   */
  @PutMapping(Api.Exam.UPDATE_COMMON)
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
   * @param multipartFile - multipart file.
   * @return
   */
  @PostMapping(Api.Exam.IMPORT_EXCEL)
  public ResponseEntity<String> readExcelFile(
      @RequestParam("multipartFile") MultipartFile multipartFile) {

    return examService.readFileExcel(multipartFile);
  }

  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 2:22:44 PM
   * Description: TODO - check empty question in exam.
   * @param examId - exam id.
   * @return
   */
  @GetMapping(value = Api.Exam.IS_EMPTY_QUESTION)
  public ResponseEntity<String> isEmptyQuestion(@PathVariable String examId) {
    boolean success = examService.isEmptyQuestionOfExam(examId);
    System.out.println(examId);
    if (success) {
      return ResponseEntity.ok(Constants.Exam.OK);
    }
    return ResponseEntity.ok(Constants.Exam.NOT_OK);
  }
}