package com.cmcglobal.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import com.cmcglobal.entity.Exam;

public interface ExamService {
  /**
   * Author: ntmduyen. Created date: Feb 20, 2019 Created time: 12:41:55 PM
   * Description: TODO - .
   * 
   * @return
   */
  public List<Exam> findAll();

  public List<Exam> pageExam(String searchContent, Sort pageable);

  public List<Exam> pageExamSortByUserCreatedByAsc(String searchContent);

  public List<Exam> pageExamSortByUserCreatedByDesc(String searchContent);

  public List<Exam> pageExamSortByCategoryAsc(String searchContent);

  public List<Exam> pageExamSortByCategoryDesc(String searchContent);

  public Exam findByID(String id);

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:01:15 PM
   * Description: TODO - approve exam to public.
   * 
   * @param examId
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
  public boolean randomQuestion(String examId, int numberRandom);

  /**
   * Author: Sanero. Created date: Feb 19, 2019 Created time: 4:00:26 PM
   * Description: TODO - remove question from exam.
   * 
   * @param exam
   * @return
   */
  public boolean removeQuestion(Exam exam);

  /**
   * Author: Sanero. Created date: Feb 14, 2019 Created time: 8:35:49 AM
   * Description: TODO - add list question to exam.
   * 
   * @param exam
   */
  public boolean addListQuestion(Exam exam);

  /**
   * Author: ptphuong. Created date: Feb 15, 2019 Created time: 5:22:39 AM
   * Description: TODO - .
   * 
   * @param ex
   */
  public void createExam(Exam ex);

  /**
   * Author: ptphuong. Created date: Feb 15, 2019 Created time: 7:55:39 PM
   * Description: TODO - .
   * 
   * @return
   */
  public String createId();

  /**
   * Author: ndvan. Created date: Feb 15, 2019 Created time: 5:22:39 AM
   * Description: TODO - .
   * 
   * @param ex
   */
  public void deleteExam(String examId);

  public List<Exam> FilterExam(Exam exam);

  public String createId1();

  Exam getOne(String examId);

  Exam insert(Exam exam);

  Exam update(Exam exam);

  List<Exam> readExcel(String exelFilePath) throws Exception;

  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 11:13:23 AM
   * Description: TODO - check empty question. if true. change status to draft.
   * @param examId
   * @return
   */
  public boolean isEmptyQuestionOfExam(String examId);
}
