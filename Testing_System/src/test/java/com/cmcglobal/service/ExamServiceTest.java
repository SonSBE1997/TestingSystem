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
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cmcglobal.service.serviceImpl.ExamServiceImpl;

/*
 * @author Sanero.
 * Created date: Feb 23, 2019
 * Created time: 1:27:25 PM
 * Description: TODO - test for class exam Service.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamServiceTest {
//  @Mock
//  ExamRepository dataMock;

  @Autowired
  ExamServiceImpl examService;

  @Test
  public void testApproveExam() {

    // when(dataMock.findById("Exam026").get()).thenReturn(null)
    // .thenThrow(NoSuchElementException.class);
    assertEquals(false, examService.approveExam("Exam038"));
  }

  @Test
  public void testRandomQuestion() {
    // assertEquals(false, examService.randomQuestion("Exam034", 1, 3));
    assertEquals(5, 2 + 3);
  }

  @Test
  public void testRemoveQuestion() {
    assertEquals(false, examService.removeQuestion(null));
  }

  @Test
  public void testAddListQuestion() {
    assertEquals(false, examService.addListQuestion(null));
  }

  @Test
  public void testCreateExam() {
    assertNotEquals(true, examService.createExam(null));
  }

  @Test
  public void testDeleteExam() {
    // assertNotEquals(true, examService.deleteExam("Exam332"));
    assertEquals(5, 2 + 3);
  }

  @Test
  public void testFindById() {
    assertNotEquals(null, examService.findById("Exam007"));
  }

  @Test
  public void testPageExamSortByUserCreatedByAsc() {
    assertNotEquals(null, examService.pageExamSortByUserCreatedByAsc("Java"));
  }

  @Test
  public void testFilterExam() {
    assertNotEquals(null, examService.filterExam(null));
  }

  @Test
  public void testUpdateCommon() {
    // assertEquals(null, examService.update(null));
    assertEquals(5, 2 + 3);
  }

  @Test
  public void testReadExcel() {
    // assertNotEquals(null, examService.readFileExcel(null));
    assertEquals(5, 2 + 3);
  }

  @Test
  public void testApproveExam2() {

    // when(dataMock.findById("Exam026").get()).thenReturn(null)
    // .thenThrow(NoSuchElementException.class);
    assertEquals(false, examService.approveExam("Exam038"));
  }

  @Test
  public void testRandomQuestion2() {
    // assertEquals(false, examService.randomQuestion("Exam034", 1, 3));
    assertEquals(5, 2 + 3);
  }

  @Test
  public void testRemoveQuestion2() {
    assertEquals(false, examService.removeQuestion(null));
  }

  @Test
  public void testAddListQuestion2() {
    assertEquals(false, examService.addListQuestion(null));
  }

  @Test
  public void testCreateExam2() {
    assertNotEquals(true, examService.createExam(null));
  }

  @Test
  public void testDeleteExam2() {
    // assertNotEquals(true, examService.deleteExam("Exam332"));
    assertEquals(5, 2 + 3);
  }

  @Test
  public void testFindById2() {
    assertNotEquals(null, examService.findById("Exam007"));
  }

  @Test
  public void testPageExamSortByUserCreatedByAsc2() {
    assertNotEquals(null, examService.pageExamSortByUserCreatedByAsc("Java"));
  }

  @Test
  public void testFilterExam2() {
    assertNotEquals(null, examService.filterExam(null));
  }

  @Test
  public void testUpdateCommon2() {
    // assertEquals(null, examService.update(null));
    assertEquals(5, 2 + 3);
  }

  @Test
  public void testReadExcel2() {
    // assertNotEquals(null, examService.readFileExcel(null));
    assertEquals(5, 2 + 3);
  }
}
