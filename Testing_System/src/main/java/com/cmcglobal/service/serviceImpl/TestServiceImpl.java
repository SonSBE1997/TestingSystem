package com.cmcglobal.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.entity.Candidate;
import com.cmcglobal.entity.CandidateTest;
import com.cmcglobal.entity.SemesterExam;
import com.cmcglobal.entity.Test;
import com.cmcglobal.entity.TestDetail;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.CandidateRepository;
import com.cmcglobal.repository.CandidateTestRepository;
import com.cmcglobal.repository.SemesterExamRepository;
import com.cmcglobal.repository.TestRepository;
import com.cmcglobal.repository.UserRepository;
import com.cmcglobal.service.ServiceResult;
import com.cmcglobal.service.ServiceResult.Status;
import com.cmcglobal.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private SemesterExamRepository semesterExamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CandidateTestRepository candidateTestRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    /* (non-Javadoc)
     * @see com.cmcglobal.service.TestService#findAll()
     */
    @Override
    public List<Test> findAll() {
        // TODO Auto-generated method stub
        return testRepository.findAll();
    }

    /* (non-Javadoc)
     * @see com.cmcglobal.service.TestService#insertTest(com.cmcglobal.entity.Test)
     */
    @Override
    public ServiceResult insertTest(Test test) {
        ServiceResult result = new ServiceResult();
        result.setData(testRepository.save(test));
        return result;

    }

    /* (non-Javadoc)
     * @see com.cmcglobal.service.TestService#deleteTestID(java.lang.Integer)
     */
    @Override
    public ServiceResult deleteTest(Integer id) {
        ServiceResult result = new ServiceResult();
        Test test = testRepository.findById(id).orElse(null);
        if (test == null) {
            result.setMessage("Not Found object");
            result.setStatus(Status.FAILED);
        } else {
            testRepository.delete(test);
            result.setStatus(Status.SUCCESS);
            result.setData(testRepository.findAll());
        }
        return result;

    }

    /* (non-Javadoc)
     * @see com.cmcglobal.service.TestService#findOne(java.lang.Integer)
     */
    @Override
    public Test findOne(Integer id) {
        return testRepository.findById(id).get();
    }

    /* (non-Javadoc)
     * @see com.cmcglobal.service.TestService#findBySemesterID(java.lang.String)
     */
    @Override
    public List<Test> findBySemesterID(String id) {
        List<Test> test = testRepository.findAll();
        List<Test> test2 = new ArrayList<>();
        for (Test test3 : test) {
            if (test3.getSemesterExam().getId().equals(id)) {
                test2.add(test3);
            }
        }
        return test2;
    }
    
    public List<TestDetail> getTestDetail(String userId, String semesterId) {
      List<TestDetail> testDetailList = new ArrayList<TestDetail>();
      SemesterExam exam = semesterExamRepository.findById(semesterId).get();
      List<Test> testList = testRepository.findBySemesterExam(exam);
      for (Test test : testList) {
        testDetailList.add(new TestDetail(test, 0));
      }
      User user = userRepository.getOne(Integer.parseInt(userId));
      List<Candidate> candidateList = candidateRepository.findByUser(user);
      List<CandidateTest> candidateTestList = candidateTestRepository.findAll();
      testList = new ArrayList<Test>();
      for (Candidate candidate : candidateList) {
        for (CandidateTest candidateTest : candidateTestList) {
          if (candidateTest.getCandidates().getId() == candidate.getId()) {
            testList.add(candidateTest.getTests());
          }
        }
      }

      for (TestDetail testDetail : testDetailList) {
        for (Test tests : testList) {
          if (testDetail.getTest().getTestID() == tests.getTestID()) {
            testDetail.setIsTest(1);
          }
        }
      }
      return testDetailList;
    }

}