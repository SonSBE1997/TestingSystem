
package com.cmcglobal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "answer_id")
	private String answerId;
	private String content;

	@Column(name = "is_true")
	private boolean isTrue;
	private int status;
	
	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
