package com.cmcglobal.service;

import java.util.List;

import com.cmcglobal.entity.Exam;

public interface ExamService {
  public List<Exam> findAll();

  public Exam findByID(String id);

  public boolean approveExam(String examId);

  public boolean randomQuestion(String examId);

  public boolean removeQuestion(Exam exam);

  /**
   * Author: Sanero.
   * Created date: Feb 14, 2019
   * Created time: 8:35:49 AM
   * Description: TODO - .
   * @param exam
   */
  public void addListQuestion(Exam exam);

  /**
   * Author: ptphuong.
   * Created date: Feb 15, 2019
   * Created time: 5:22:39 AM
   * Description: TODO - .
   * @param ex
   */
  public void createExam(Exam ex);
}
