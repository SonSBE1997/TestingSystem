package com.cmcglobal.entity;

import java.io.Serializable;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	private String choice_order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getChoice_order() {
		return choice_order;
	}

	public void setChoice_order(String choice_order) {
		this.choice_order = choice_order;
	}

}
