package com.cmcglobal.service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.ExamQuestion;
import com.cmcglobal.entity.Question;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.service.ExamQuestionService;
import com.cmcglobal.service.ExamService;
import com.cmcglobal.service.QuestionServices;
import com.cmcglobal.utils.Helper;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
  @Autowired
  ExamRepository examRepository;

  @Autowired
  ExamQuestionService examQuestionService;

  @Autowired
  QuestionServices questionService;

  @Override
  public void createExam(Exam ex) {
    User user = new User();
    user.setUserId(1);
    ex.setExamId(this.createId());
    ex.setNote(ex.getNote().substring(3, ex.getNote().length() - 4));
    ex.setUserCreated(user);
    ex.setCreateAt(new Date());
    examRepository.save(ex);
  }

  @Override
  public List<Exam> findAll() {
    return examRepository.findAll();
  }

  @Override
  public Exam findByID(String id) {
    return examRepository.findById(id).get();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmcglobal.service.ExamService#approveExam(java.lang.String) Author:
   * Sanero. Created date: Feb 13, 2019 Created time: 1:45:04 PM
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

  /*
   * (non-Javadoc)
   * 
   * @see com.cmcglobal.service.ExamService#randomQuestion(java.lang.String)
   * Author: Sanero. Created date: Feb 13, 2019 Created time: 4:17:07 PM
   */
  @Override
  public boolean randomQuestion(String examId, int numberRandom) {
//    Exam exam = examRepository.findById(examId).get();
    Random random = new Random();
    List<Question> questions = questionService.getAllQuestion();
    List<ExamQuestion> examQuestions = Helper.randomQuestion(random, questions,
        numberRandom, examId);
    for (ExamQuestion examQuestion : examQuestions) {
      examQuestion.setExamId(examId);
      String choiceOrder = Helper.randomChoiceOrder(random);
      examQuestion.setChoiceOrder(choiceOrder);
      examQuestionService.insert(examQuestion);
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmcglobal.service.ExamService#removeQuestion(com.cmcglobal.entity.Exam)
   * Author: Sanero. Created date: Feb 13, 2019 Created time: 5:25:05 PM
   */
  @Override
  public boolean removeQuestion(Exam exam) {
    // Exam updateExam = examRepository.findById(exam.getExamId()).get();
    // updateExam.setExamQuestions(exam.getExamQuestions());
    // updateExam = examRepository.save(updateExam);
    for (ExamQuestion examQuestion : exam.getExamQuestions()) {
      examQuestionService.deleteById(examQuestion.getId());
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmcglobal.service.ExamService#addListQuestion(com.cmcglobal.entity.Exam)
   * Author: Sanero. Created date: Feb 14, 2019 Created time: 8:36:00 AM
   */
  @Override
  public void addListQuestion(Exam exam) {
    String examId = exam.getExamId();
    Random random = new Random();
    for (ExamQuestion examQuestion : exam.getExamQuestions()) {
      examQuestion.setExamId(examId);
      String choiceOrder = Helper.randomChoiceOrder(random);
      examQuestion.setChoiceOrder(choiceOrder);
      examQuestionService.insert(examQuestion);
    }
  }

  @Override
  public List<Exam> pageExam(Pageable pageable) {
    return examRepository.pageExam(pageable);
  }

  @Override
  public String createId() {
    String id;
    List<Exam> exam = examRepository.findAll();
    int ids = exam.size() - 1;
    String tmp = exam.get(ids).getExamId();
    tmp = tmp.substring(tmp.length() - 3, tmp.length());
    int id1 = Integer.parseInt(tmp) + 1;
    if (id1 < 10)
      id = ("Exam00") + id1;
    else if (id1 > 9 && id1 < 100)
      id = ("Exam0") + id1;
    else
      id = ("Exam") + id1;
    return id;
  }

  @Override
  public List<Exam> pageExamSortByUserCreatedByAsc(Pageable pageable) {
    return examRepository.pageExamSortByUserCreatedByAsc(pageable);
  }

  @Override
  public List<Exam> pageExamSortByUserCreatedByDesc(Pageable pageable) {
    return examRepository.pageExamSortByUserCreatedByDesc(pageable);
  }

}
