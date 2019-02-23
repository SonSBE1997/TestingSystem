package com.cmcglobal.service;

import com.cmcglobal.entity.Exam;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/*
 * @author Sanero.
 * Created date: Feb 22, 2019
 * Created time: 4:59:58 PM
 * Description: TODO - Exam service.
 */
public interface ExamService {
  /**
   * Author: ntmduyen. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - find all exam.
   * 
   * @return
   */
  public List<Exam> findAll();

  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 1:51:45 PM
   * Description: TODO - show list exam.
   * @param searchContent - search content.
   * @param pageable - pageable.
   * @return
   */
  public List<Exam> pageExam(String searchContent, Sort pageable);

  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 1:51:50 PM
   * Description: TODO - sort exam by user created ascending.
   * @param searchContent - search content.
   * @return
   */
  public List<Exam> pageExamSortByUserCreatedByAsc(String searchContent);

  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 1:51:54 PM
   * Description: TODO - sort exam by user created descending.
   * @param searchContent - search content.
   * @return
   */
  public List<Exam> pageExamSortByUserCreatedByDesc(String searchContent);

  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 1:51:58 PM
   * Description: TODO - Sort exam by category ascending.
   * @param searchContent - search content.
   * @return
   */
  public List<Exam> pageExamSortByCategoryAsc(String searchContent);

  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 1:52:03 PM
   * Description: TODO - ort exam by category descendingly .
   * @param searchContent - search content.
   * @return
   */
  public List<Exam> pageExamSortByCategoryDesc(String searchContent);

  /**
   * Author: vvdong. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - find exam by exam-id.
   * 
   * @return
   */
  public Exam findById(String id);

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:01:15 PM
   * Description: TODO - approve exam to public.
   * 
   * @param examId - examId.
   * @return
   */
  public boolean approveExam(String examId);

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:00:44 PM
   * Description: TODO - random question to exam.
   * 
   * @param examId       - exam id.
   * @param numberRandom - number of random question.
   * @return
   */
  public boolean randomQuestion(String examId, int categoryId,
      int numberRandom);

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:00:26 PM
   * Description: TODO - remove question from exam.
   * 
   * @param exam - exam.
   * @return
   */
  public boolean removeQuestion(Exam exam);

  /**
   * Author: Sanero. Created date: Feb 14, 2019 Created time: 8:35:49 AM
   * Description: TODO - add list question to exam.
   * 
   * @param exam - exam.
   */
  public boolean addListQuestion(Exam exam);

  /**
   * Author: ptphuong. Created date: Feb 15, 2019 Created time: 5:22:39 AM
   * Description: TODO - create exam.
   * 
   * @param ex - exam.
   */
  public void createExam(Exam ex);

  /**
   * Author: ptphuong. Created date: Feb 15, 2019 Created time: 7:55:39 PM
   * Description: TODO - create id - generate id.
   * 
   * @return
   */
  public String createId();

  /**
   * Author: ndvan. Created date: Feb 15, 2019 Created time: 5:22:39 AM
   * Description: TODO - delete exam.
   * 
   * @param examId - exam Id.
   */
  public void deleteExam(String examId);

  /**
   * Author: ndvan. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - filter exam.
   * 
   * @param exam - exam.
   * @return
   */
  public List<Exam> filterExam(Exam exam);

  /**
   * Author: hai95. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - get exam by id.
   * 
   * @return
   */
  Exam getOne(String examId);

  /**
   * Author: hai95. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - insert exam to db.
   * 
   * @return
   */
  Exam insert(Exam exam);

  /**
   * Author: hai95. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - update exam.
   * 
   * @return
   */
  Exam update(Exam exam);

  /**
   * Author: hai95. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - read file excel.
   * 
   * @return
   */
  List<Exam> readExcel(String exelFilePath) throws Exception;

  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 11:13:23 AM
   * Description: TODO - check empty question. if true. change status to draft.
   * @param examId - exam Id.
   * @return
   */
  public boolean isEmptyQuestionOfExam(String examId);

  /**
   * Author: ntmduyen.
   * Created date: Feb 23, 2019
   * Created time: 5:21:58 PM
   * Description: TODO - .
   * @param sortOrder - sort order.
   * @param sortTerm - sort term. 
   * @param searchContent - search content.
   * @return
   */
  public List<Exam> getListExamByPage(String sortOrder, String sortTerm,
      String searchContent);

  /**
   * Author: hai95.
   * Created date: Feb 23, 2019
   * Created time: 6:02:34 PM
   * Description: TODO - .
   * @param multipartFile - multipart file.
   * @return
   */
  public ResponseEntity<String> readFileExcel(MultipartFile multipartFile);
}
