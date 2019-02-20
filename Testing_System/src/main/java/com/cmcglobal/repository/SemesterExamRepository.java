package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmcglobal.entity.SemesterExam;

@Repository
public interface SemesterExamRepository extends JpaRepository<SemesterExam, String> {

	List<SemesterExam> findByNameContaining(String keyword);

	@Query(value = "select * from semester_exam p where p.semester_exam_name like %:name%", nativeQuery = true)
	List<SemesterExam> filterByAll(String name);

}
