package com.cmcglobal.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.service.ExamQuestionService;
import com.cmcglobal.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
  @Autowired
  ExamRepository examRepository;

  @Autowired
  ExamQuestionService examQuestionService;

  @Override
  public List<Exam> findAll() {
    return examRepository.findAll();
  }

  @Override
  public Exam findByID(String id) {
    return examRepository.findById(id).get();
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.ExamService#approveExam(java.lang.String)
   * Author: Sanero.
   * Created date: Feb 13, 2019
   * Created time: 1:45:04 PM
   */
  @Override
  public boolean approveExam(String examId) {
    Exam exam = examRepository.findById(examId).get();
    exam.setStatus("Public");
    exam = examRepository.save(exam);
    if ("Public".equals(exam.getStatus())) {
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.ExamService#randomQuestion(java.lang.String)
   * Author: Sanero.
   * Created date: Feb 13, 2019
   * Created time: 4:17:07 PM
   */
  @Override
  public boolean randomQuestion(String examId) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.ExamService#removeQuestion(com.cmcglobal.entity.Exam)
   * Author: Sanero.
   * Created date: Feb 13, 2019
   * Created time: 5:25:05 PM
   */
  @Override
  public boolean removeQuestion(Exam exam) {
    Exam updateExam = examRepository.findById(exam.getExamId()).get();
    updateExam.setExamQuestions(exam.getExamQuestions());
    updateExam = examRepository.save(updateExam);
    return true;
  }

}
