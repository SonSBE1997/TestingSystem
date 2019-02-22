package com.cmcglobal.repository;

import com.cmcglobal.entity.Candidate;
import com.cmcglobal.entity.SemesterExam;
import com.cmcglobal.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
  public List<Candidate> findBySemesterExam(SemesterExam semesterExam);

  public List<Candidate> findByUser(User user);
}
