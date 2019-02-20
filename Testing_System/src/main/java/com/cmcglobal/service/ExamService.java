package com.cmcglobal.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

import com.cmcglobal.entity.Exam;

public interface ExamService {
  public List<Exam> findAll();

  public List<Exam> pageExam(Pageable pageable);

  public List<Exam> pageExamSortByUserCreatedByAsc(Pageable pageable);

  public List<Exam> pageExamSortByUserCreatedByDesc(Pageable pageable);

  public List<Exam> pageExamSortByCategoryAsc(Pageable pageable);

  public List<Exam> pageExamSortByCategoryDesc(Pageable pageable);

  public Exam findByID(String id);

  public boolean approveExam(String examId);

  public boolean randomQuestion(String examId, int numberRandom);

  public boolean removeQuestion(Exam exam);

  /**
   * Author: Sanero. Created date: Feb 14, 2019 Created time: 8:35:49 AM
   * Description: TODO - .
   * 
   * @param exam
   */
  public void addListQuestion(Exam exam);

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
}
