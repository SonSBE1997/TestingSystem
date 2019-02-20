package com.cmcglobal.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Candidate;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.SemesterExam;
import com.cmcglobal.entity.Test;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.CandidateRepository;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.repository.SemesterExamRepository;
import com.cmcglobal.repository.TestRepository;
import com.cmcglobal.repository.UserRepository;
import com.cmcglobal.service.ServiceResult;
import com.cmcglobal.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private TestRepository testRepository;
	@Autowired
	private SemesterExamRepository semesterRepo;

	@Override
	public ServiceResult getSemseterListByUserId(String id) {
		ServiceResult result = new ServiceResult();
		User user = userRepository.findById(Integer.parseInt(id)).get();
		List<Candidate> candidate = candidateRepository.findByUser(user);
		List<SemesterExam> semesters = new ArrayList<SemesterExam>();

		for (Candidate ca : candidate) {
			semesters.add(ca.getSemesterExam());
		}
		result.setData(semesters);
		return result;
	}

	@Override
	public ServiceResult getExamBySemesterExamId(String id) {
		ServiceResult result = new ServiceResult();
		SemesterExam semesterExam = semesterRepo.getOne(id);
		List<Exam> exams = new ArrayList<>();
		List<Test> list = testRepository.findBySemesterExam(semesterExam);
		for (Test test : list) {
			exams.add(test.getExam());
		}
		result.setData(exams);
		return result;
	}

}
