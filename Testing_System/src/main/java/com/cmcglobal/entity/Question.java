package com.cmcglobal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "question_id")
	private int questionId;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	private String content;
	@Column(name = "type_id")
	private int typeId;
	@Column(name = "level_id")
	private int levelId;
	private String sugguestion;
	@Column(name = "tag_id")
	private int tag_id;
	private int status;
	@Column(name = "user_id_created")
	private int userIdCreated;
	@Column(name = "date_created")
	private Date dateCreated;
	@OneToMany(mappedBy = "question")
	private List<Answer> answers;
	@OneToMany(mappedBy="exam",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<ExamQuestion> examQuestions;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getSugguestion() {
		return sugguestion;
	}

	public void setSugguestion(String sugguestion) {
		this.sugguestion = sugguestion;
	}

	public int getTag_id() {
		return tag_id;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserIdCreated() {
		return userIdCreated;
	}

	public void setUserIdCreated(int userIdCreated) {
		this.userIdCreated = userIdCreated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<ExamQuestion> getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(List<ExamQuestion> examQuestions) {
		this.examQuestions = examQuestions;
	}
	

}
