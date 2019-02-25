package com.cmcglobal.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cmcglobal.entity.Answer;
import com.cmcglobal.entity.Category;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.ExamQuestion;
import com.cmcglobal.entity.Question;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.service.serviceImpl.ExamServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ExamControllerTest {
//  @Autowired
//  private MockMvc mockMvc;

  @Mock
  private ExamRepository repository;

  @InjectMocks
  private ExamServiceImpl examService;

//  @InjectMocks
//  private ExamController examController;

  List<Exam> listExam;
  Exam e1;
  Exam e2;
  Exam e3;
  Exam e4;
  Exam e5;
  Category cate;
  Set<ExamQuestion> examQuestions;
  ExamQuestion q1;
  ExamQuestion q2;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    listExam = new ArrayList<Exam>();
    cate = new Category();
    cate.setCategoryId(1);
    cate.setCategoryName("java");
    Question question = new Question();
    question.setQuestionId("Q1");
    question.setContent("content");

    Answer answer = new Answer();
    answer.setAnswerId("A1");
    answer.setTrue(true);

    List<Answer> listAnswer = new ArrayList<Answer>();
    listAnswer.add(answer);
    listAnswer.add(answer);

    question.setAnswers(listAnswer);

    examQuestions = new HashSet<ExamQuestion>();
    q1 = new ExamQuestion();
    q2 = new ExamQuestion();
    q1.setId(1);
    q2.setId(2);
    q1.setChoiceOrder("1 2");
    q2.setChoiceOrder("1 2");

    q1.setQuestion(question);
    q2.setQuestion(question);
    examQuestions.add(q1);
    examQuestions.add(q2);

    e1 = new Exam("Exam001", "Java basic1", 30, "no note", "Draft", true,
        sqlDate, null, 10, cate, null, null, examQuestions);
    e2 = new Exam("Exam002", "Java basic2", 40, "no note", "Draft", true,
        sqlDate, null, 15, cate, null, null, null);
    e3 = new Exam("Exam003", "Java basic3", 20, "no note", "Draft", true,
        sqlDate, null, 16, cate, null, null, null);
    e4 = new Exam("Exam004", "Java basic4", 10, "no note", "Draft", true,
        sqlDate, null, 20, cate, null, null, null);
    e5 = new Exam("Exam005", "Java basic5", 30, "no note", "Draft", true,
        sqlDate, null, 30, cate, null, null, null);

    listExam.add(e1);
    listExam.add(e2);
    listExam.add(e3);
    listExam.add(e4);
    listExam.add(e5);
  }

  @Test
  public void testFindAll() {
    when(repository.findAll()).thenReturn(listExam);
    assertEquals(5, examService.findAll().size());
  }

  @Test
  public void testCreate() {
    Mockito.when(repository.save(e1)).thenReturn(e1);
    assertEquals(true, examService.createExam(e1));
  }

//  @Test
//  public void testFindById() {
//    Mockito.when(repository.findById("Exam001").get()).thenReturn(e1);
//    assertEquals(e1, examService.findById("Exam001"));
//  }

//  @Test
//  public void testApprove() {
//    when(examService.approveExam("Exam001")).thenReturn(true);
//    assertEquals(true, examService.approveExam("Exam001"));
//  }

//  @Test
//  public void testApprove() {
//    Exam approveExam = new Exam(e1.getExamId(), e1.getTitle(), e1.getDuration(), e1.getNote(), "Public", true,
//        e1.getCreateAt(), null, 10, cate, null, null, examQuestions);
//    when(repository.save(e1)).thenReturn(approveExam);
//    assertEquals(true, examService.approveExam(e1.getExamId()));
//  }
}

//@RequestMapping(Api.Exam.BASE_URL)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestingSystemApplication.class)
//@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class ExamControllerTest {
//private MockMvc mockMvc;
//  
//  @Autowired
//    private WebApplicationContext wac;
//
//  @Before
//  public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//
//  }
//  
//  @Test
//  public void verifyListExams() throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get(Api.Exam.GET_ALL).accept(MediaType.APPLICATION_JSON))
//      .andExpect(jsonPath("$", hasSize(15))).andDo(print());
//  }
//}
