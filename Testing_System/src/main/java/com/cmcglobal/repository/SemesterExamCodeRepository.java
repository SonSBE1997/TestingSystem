package com.cmcglobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmcglobal.entity.SemesterExamCode;

public interface SemesterExamCodeRepository extends JpaRepository<SemesterExamCode, Integer>{
  SemesterExamCode findByCode(String code);
}
