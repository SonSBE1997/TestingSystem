package com.cmcglobal.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.entity.SemesterExamCode;
import com.cmcglobal.repository.SemesterExamCodeRepository;

@Service
@Transactional
public class SemesterExamCodeServiceImpl {
  @Autowired
  SemesterExamCodeRepository repo;

  public SemesterExamCode findByCode(String code) {
    return repo.findByCode(code);
  }
}
