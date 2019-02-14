package com.cmcglobal.service;

import java.util.List;

import com.cmcglobal.entity.Exam;

public interface ExamService {
	public List<Exam> findAll();
	public Exam findByID(String id);
}
