package com.cmcglobal.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

import com.cmcglobal.entity.Exam;

public interface ExamService {
	/**
	 * Author: ntmduyen
	 * Created date: Feb 14, 2019
	 * Created time: 5:12:48 PM
	 * Description: TODO - find all exam.
	 * @return
	 */
	public List<Exam> findAll();

	/**
	 * Author: ntmduyen
	 * Created date: Feb 14, 2019
	 * Created time: 5:12:48 PM
	 * Description: TODO - pagination .
	 * @return
	 */
	public List<Exam> pageExam(Pageable pageable);
	/**
	 * Author: ntmduyen
	 * Created date: Feb 15, 2019
	 * Created time: 8:35:47 AM
	 * Description: TODO - .
	 * @param contentSearch
	 * @return
	 */
	public List<Exam> pageExamSortByUserCreatedByAsc(Pageable pageable);
	public List<Exam> pageExamSortByUserCreatedByDesc(Pageable pageable);
	public Exam findByID(String id);

	public boolean approveExam(String examId);

	public boolean randomQuestion(String examId);

	public boolean removeQuestion(Exam exam);

	/**
	 * Author: Sanero.
	 * Created date: Feb 14, 2019
	 * Created time: 8:35:49 AM
	 * Description: TODO - .
	 * @param exam
	 */
	public void addListQuestion(Exam exam);

	 /**
	   * Author: ptphuong.
	   * Created date: Feb 15, 2019
	   * Created time: 5:22:39 AM
	   * Description: TODO - .
	   * @param ex
	   */
	  public void createExam(Exam ex);
}