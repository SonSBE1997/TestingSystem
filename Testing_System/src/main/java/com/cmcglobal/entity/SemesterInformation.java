package com.cmcglobal.entity;

public class SemesterInformation {
	private SemesterExam semesterExam;
	
//	jsonInfo.put("total_number_exam", exams.size());
//	jsonInfo.put("total_number_question", total_number_question);
//	jsonInfo.put("total_user_join", user_join.size());
	private int total_number_exam;
	private int total_number_question;
	private int total_user_join;
	public SemesterInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SemesterExam getSemesterExam() {
		return semesterExam;
	}
	public void setSemesterExam(SemesterExam semesterExam) {
		this.semesterExam = semesterExam;
	}
	public int getTotal_number_exam() {
		return total_number_exam;
	}
	public void setTotal_number_exam(int total_number_exam) {
		this.total_number_exam = total_number_exam;
	}
	public int getTotal_number_question() {
		return total_number_question;
	}
	public void setTotal_number_question(int total_number_question) {
		this.total_number_question = total_number_question;
	}
	public int getTotal_user_join() {
		return total_user_join;
	}
	public void setTotal_user_join(int total_user_join) {
		this.total_user_join = total_user_join;
	}
	

}
