
package com.cmcglobal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "semester_exam_code")
public class SemesterExamCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "semester_exam_codecol")
	private String code;

	@Column(name = "status")
	private String status;
	
	public String getSemesterExamId() {
    return semesterExamId;
  }

  public void setSemesterExamId(String semesterExamId) {
    this.semesterExamId = semesterExamId;
  }

  @Column(name="semester_exam_id")
	private String semesterExamId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
