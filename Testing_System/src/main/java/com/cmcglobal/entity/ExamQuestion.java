package com.cmcglobal.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exam_question")
public class ExamQuestion implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "exam_id")
  private String examId;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;
  @Column(name = "choice_order")
  private String choiceOrder;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public String getChoiceOrder() {
    return choiceOrder;
  }

  public void setChoiceOrder(String choiceOrder) {
    this.choiceOrder = choiceOrder;
  }

  public String getExamId() {
    return examId;
  }

  public void setExamId(String examId) {
    this.examId = examId;
  }
}
