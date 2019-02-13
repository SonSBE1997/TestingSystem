package com.cmcglobal.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.repository.ExamRepository;

@Service
@Transactional
public class ExamService {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	ExamRepository examRepository;
	
	public Exam getOne(String examId) {
		return entityManager.find(Exam.class, examId);
	}
	
	public Exam update(Exam exam) {
		return entityManager.merge(exam);
	}
}
