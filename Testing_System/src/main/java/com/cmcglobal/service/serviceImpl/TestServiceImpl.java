package com.cmcglobal.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.entity.Test;
import com.cmcglobal.repository.TestRepository;
import com.cmcglobal.service.ServiceResult;
import com.cmcglobal.service.TestService;
import com.cmcglobal.service.ServiceResult.Status;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

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

}