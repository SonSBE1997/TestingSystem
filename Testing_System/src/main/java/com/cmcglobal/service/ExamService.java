package com.cmcglobal.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

import com.cmcglobal.entity.Exam;

public interface ExamService {

    /**
     * Author: ntmduyen Created date: Feb 14, 2019 Created time: 5:12:48 PM
     * Description: TODO - find all exam.
     * 
     * @return
     */
    public List<Exam> findAll();

    /**
     * Author: ntmduyen Created date: Feb 14, 2019 Created time: 5:12:48 PM
     * Description: TODO - pagination .
     * 
     * @return
     */
    public List<Exam> pageExam(Pageable pageable);

    /**
     * Author: ntmduyen Created date: Feb 15, 2019 Created time: 8:35:47 AM
     * Description: TODO - .
     * 
     * @param contentSearch
     * @return
     */
    public List<Exam> pageExamSortByUserCreatedByAsc(Pageable pageable);

    public List<Exam> pageExamSortByUserCreatedByDesc(Pageable pageable);

    public Exam findByID(String id);

    /**
     * Author: Sanero.
     * Created date: Feb 19, 2019
     * Created time: 4:01:15 PM
     * Description: TODO - approve exam to public.
     * @param examId
     * @return
     */
    public boolean approveExam(String examId);

    /**
     * Author: Sanero.
     * Created date: Feb 19, 2019
     * Created time: 4:00:44 PM
     * Description: TODO - random question to exam.
     * @param examId - exam id.
     * @param numberRandom - number of random question.
     * @return
     */
    public boolean randomQuestion(String examId,int numberRandom);

    /**
     * Author: Sanero.
     * Created date: Feb 19, 2019
     * Created time: 4:00:26 PM
     * Description: TODO - remove question from exam.
     * @param exam
     * @return
     */
    public boolean removeQuestion(Exam exam);

    /**
     * Author: Sanero. Created date: Feb 14, 2019 Created time: 8:35:49 AM
     * Description: TODO - add list question to exam.
     * 
     * @param exam
     */
    public boolean addListQuestion(Exam exam);

    /**
     * Author: ptphuong. Created date: Feb 15, 2019 Created time: 5:22:39 AM
     * Description: TODO - .
     * 
     * @param ex
     */
    public void createExam(Exam ex);

    /**
     * Author: ptphuong. Created date: Feb 15, 2019 Created time: 7:55:39 PM
     * Description: TODO - .
     * 
     * @return
     */
    public String createId();

	/**
	 * Author: ndvan. Created date: Feb 15, 2019 Created time: 5:22:39 AM
	 * Description: TODO - .
	 * 
	 * @param ex
	 */
	public void deleteExam(String examId);

	public List<Exam> FilterExam(Exam exam);
}
