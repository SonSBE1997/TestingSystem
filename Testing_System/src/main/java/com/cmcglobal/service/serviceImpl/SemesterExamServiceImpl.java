package com.cmcglobal.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Candidate;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.SemesterExam;
import com.cmcglobal.entity.SemesterInformation;
import com.cmcglobal.entity.Test;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.CandidateRepository;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.repository.SemesterExamRepository;
import com.cmcglobal.repository.TestRepository;
import com.cmcglobal.repository.UserRepository;
import com.cmcglobal.service.SemesterExamService;
import com.cmcglobal.service.ServiceResult;
import com.cmcglobal.service.ServiceResult.Status;

@Service
public class SemesterExamServiceImpl implements SemesterExamService {

	@Autowired
	private SemesterExamRepository examRepository;

	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	TestRepository testRepository;
	@Autowired
	ExamRepository exam_repository;

	@Override
	public ServiceResult getAllSemesterExam() {
		ServiceResult result = new ServiceResult();
		result.setTotalRecord(examRepository.findAll().size());
		result.setData(examRepository.findAll());
		return result;
	}

	@Override
	public ServiceResult create(SemesterExam semesterExam) {
		ServiceResult result = new ServiceResult();
		result.setData(examRepository.save(semesterExam));
		return result;
	}

	@Override
	public ServiceResult search(String keyword) {
		ServiceResult result = new ServiceResult();
		result.setData(examRepository.findByNameContaining(keyword));
		return result;
	}

	@Override
	public ServiceResult delete(String id) {
		ServiceResult result = new ServiceResult();
		SemesterExam semesterExam = examRepository.findById(id).orElse(null);
		if (semesterExam == null) {
			result.setMessage("Not Found object");
			result.setStatus(Status.FAILED);
		} else {
			examRepository.delete(semesterExam);
			result.setStatus(Status.SUCCESS);
			result.setData(examRepository.findAll());
		}
		return result;
	}

	@Override
	public ServiceResult findById(String id) {
		ServiceResult result = new ServiceResult();
		result.setData(examRepository.findById(id));
		return result;
	}


	@Override
	public ServiceResult filter(String name) {
		ServiceResult result = new ServiceResult();
		result.setData(examRepository.filterByAll(name));
		return result;
	}

	public SemesterInformation getInformationOfSemester(String id) {

		SemesterExam semesterExam = examRepository.findById(id).get();
		List<Candidate> list_candidate = candidateRepository.findBySemesterExam(semesterExam);
		List<Test> list_test = testRepository.findBySemesterExam(semesterExam);
		List<User> user_join = new ArrayList<User>();
		List<Exam> exams = new ArrayList<Exam>();
		JSONObject jsonInfo = new JSONObject();
		int total_number_question = 0;
		for (Candidate candidate : list_candidate) {

			User user = userRepository.findById(candidate.getUser().getUserId()).get();
			user_join.add(user);

		}
		for (Test test : list_test) {
			Exam exam = exam_repository.findById(test.getExam().getExamId()).get();
			exams.add(exam);
			total_number_question += exam.getNumberOfQuestion();
		}
		
		jsonInfo.put("semesterexam", examRepository.findById(id).get().getUser());
		jsonInfo.put("total_number_exam", exams.size());
		jsonInfo.put("total_number_question", total_number_question);
		jsonInfo.put("total_user_join", user_join.size());
		
		SemesterInformation semesterInformation = new SemesterInformation();
		semesterInformation.setSemesterExam(semesterExam);
		semesterInformation.setTotal_number_exam(exams.size());
		semesterInformation.setTotal_number_question(total_number_question);
		semesterInformation.setTotal_user_join(user_join.size());
		
		//return jsonInfo.toString();
		return semesterInformation;
	}

}
