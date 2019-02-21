package com.cmcglobal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Candidate {
	@Id
	@GeneratedValue
	@Column(name = "candidate_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "semester_exam_id")
	@JsonIgnoreProperties("candidates")
	private SemesterExam semesterExam;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("candidates")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SemesterExam getSemesterExam() {
		return semesterExam;
	}

	public void setSemesterExam(SemesterExam semesterExam) {
		this.semesterExam = semesterExam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
