/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.service
 * File name: ExamServiceTest.java
 * Author: Sanero.
 * Created date: Feb 23, 2019
 * Created time: 1:27:25 PM
 */

package com.cmcglobal.service;

import static org.junit.Assert.assertEquals;

import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.service.serviceImpl.ExamServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/*
 * @author Sanero.
 * Created date: Feb 23, 2019
 * Created time: 1:27:25 PM
 * Description: TODO - test for class exam Service.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExamServiceTest {
  @Mock
  ExamRepository dataMock;

  @InjectMocks
  ExamServiceImpl examService;

  @Test
  public void testApproveExam() {

    // when(dataMock.findById("Exam026").get()).thenReturn(null)
    // .thenThrow(NoSuchElementException.class);
    assertEquals(false, examService.approveExam("Exam003"));
  }
}
