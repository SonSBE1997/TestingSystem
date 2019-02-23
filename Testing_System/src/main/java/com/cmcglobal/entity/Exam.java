package com.cmcglobal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Entity
//@SQLDelete(sql = "UPDATE Exam " + "SET is_enable = false " + "WHERE exam_Id = ?")
//@Where(clause = "is_enable = true")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "exam_Id")
	private String examId;
	private String title;
	private float duration;
	private String note;
	private String status;
	@Transient
	private String categoryName;
	@Column(name = "is_enable")
	private boolean isEnable;
	@Column(name = "create_at")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	@Column(name = "modified_at")
	private Date modifiedAt;
	@Column(name = "number_of_question")
	private int numberOfQuestion;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "create_by")

	private User userCreated;
	@ManyToOne
	@JoinColumn(name = "modified_by")
	private User modifiedBy;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_id")
	private Set<ExamQuestion> examQuestions;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "exam", orphanRemoval = true)
	private List<Test> tests;

	public String getExamId() {
		return examId;
	}

	public Set<ExamQuestion> getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(Set<ExamQuestion> examQuestions) {
		this.examQuestions = examQuestions;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public User getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@PrePersist
	protected void onCreate() {
		this.createAt = new Date();
	}
}
