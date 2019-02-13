package com.cmcglobal.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
	@Autowired
	ExamRepository examRepository;

  @Override
  public List<Exam> findAll() {
    return examRepository.findAll();
  }

  @Override
  public Exam findByID(String id) {
    return examRepository.findById(id).get();
  }


}
