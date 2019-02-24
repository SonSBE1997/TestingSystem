package com.cmcglobal;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cmcglobal.builder.FilterBuilder;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.repository.ExamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingSystemApplicationTests {
	@Autowired
	ExamRepository examRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testDelete() {
		Exam exam = examRepository.findById("de001").get();
		examRepository.delete(exam);
	}

	@Test
	public void testFilterNullValue() {

		Exam exam = new Exam();

		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterDuration() {

		Exam exam = new Exam();
		exam.setDuration(30);
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterCategory() {
		Exam exam = new Exam();
		exam.setCategoryName("Java");
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterCreateAt() {
		Exam exam = new Exam();
		String str = "2019-02-24";
		exam.setCreateAt(Date.valueOf(str));
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterNumberOfQuestion() {
		Exam exam = new Exam();
		exam.setNumberOfQuestion(30);
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterStatus() {
		Exam exam = new Exam();
		exam.setStatus("Public");
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterStatusAndNumberOfQuestion() {
		Exam exam = new Exam();
		exam.setNumberOfQuestion(30);
		exam.setStatus("Public");
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterStatusAndNumberOfQuestionAndCreateAt() {
		Exam exam = new Exam();
		String str = "2019-02-24";
		exam.setCreateAt(Date.valueOf(str));
		exam.setNumberOfQuestion(30);
		exam.setStatus("Public");
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterStatusAndNumberOfQuestionAndCreateAtAndTitle() {
		Exam exam = new Exam();
		exam.setTitle("de1");
		String str = "2019-02-24";
		exam.setCreateAt(Date.valueOf(str));
		exam.setNumberOfQuestion(30);
		exam.setStatus("Public");
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	@Test
	public void testFilterStatusAndNumberOfQuestionAndCreateAtAndTitleAndCategory() {
		Exam exam = new Exam();
		exam.setCategoryName("Java");
		exam.setTitle("de1");
		String str = "2019-02-24";
		exam.setCreateAt(Date.valueOf(str));
		exam.setNumberOfQuestion(30);
		exam.setStatus("Public");
		List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
		exams.forEach(item -> System.out.println(item.getExamId() + "-" + item.getTitle() + "-" + item.getDuration()
				+ "-" + item.getCategory().getCategoryName() + "-" + item.getNumberOfQuestion() + "-" + item.getStatus()
				+ "-" + item.getCreateAt()));

	}

	public FilterBuilder getFilterBuilder(Exam exam) {
		return new FilterBuilder.Builder().setNumberOfQuestion(exam.getNumberOfQuestion())
				.setDuration(exam.getDuration()).setStatus(exam.getStatus()).setCaterogyName(exam.getCategoryName())
				.setCreateAt(exam.getCreateAt()).builder();
	}
}
