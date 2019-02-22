package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmcglobal.entity.Candidate;
import com.cmcglobal.entity.CandidateTest;

public interface CandidateTestRepository extends JpaRepository<CandidateTest, Integer>{
	
	List<CandidateTest> findByCandidates(Candidate candidate);

}
