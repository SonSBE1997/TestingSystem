package com.cmcglobal.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.builder.FilterBuilder;
import com.cmcglobal.entity.Category;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.ExamQuestion;
import com.cmcglobal.entity.Question;
import com.cmcglobal.entity.User;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.service.CategoryService;
import com.cmcglobal.service.ExamQuestionService;
import com.cmcglobal.service.ExamService;
import com.cmcglobal.service.QuestionServices;
import com.cmcglobal.utils.Helper;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
  @Autowired
  ExamRepository examRepository;

  @Autowired
  ExamQuestionService examQuestionService;

  @Autowired
  QuestionServices questionService;

  @Autowired
  EntityManager entityManager;

  @Autowired
  ExamService examService;

  @Override
  public void createExam(Exam ex) {
    User user = new User();
    user.setUserId(1);
    ex.setExamId(this.createId());
    ex.setTitle(ex.getTitle().trim());
    ex.setNote(ex.getNote().substring(3, ex.getNote().length() - 4));
    ex.setUserCreated(user);
    ex.setCreateAt(new Date());
    ex.setEnable(true);
    examRepository.save(ex);
  }

  @Override
  public List<Exam> findAll() {
    return examRepository.findAll();
  }

  @Override
  public Exam findByID(String id) {
    return examRepository.findById(id).get();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmcglobal.service.ExamService#approveExam(java.lang.String) Author:
   * Sanero. Created date: Feb 13, 2019 Created time: 1:45:04 PM
   */
  @Override
  public boolean approveExam(String examId) {
    try {
      Exam exam = examRepository.findById(examId).get();
      if (exam.getExamQuestions().size() == 0)
        return false;
      exam.setStatus("Public");
      exam = examRepository.save(exam);
      if ("Public".equals(exam.getStatus())) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public List<Exam> pageExam(String searchContent, Sort pageable) {
    return examRepository.pageExam(searchContent, pageable);
  }

  @Override
  public List<Exam> pageExamSortByUserCreatedByAsc(String searchContent) {
    return examRepository.pageExamSortByUserCreatedByAsc(searchContent);
  }

  @Override
  public List<Exam> pageExamSortByUserCreatedByDesc(String searchContent) {
    return examRepository.pageExamSortByUserCreatedByDesc(searchContent);
  }

  @Override
  public List<Exam> pageExamSortByCategoryAsc(String searchContent) {
    return examRepository.pageExamSortByCategoryAsc(searchContent);
  }

  @Override
  public List<Exam> pageExamSortByCategoryDesc(String searchContent) {
    return examRepository.pageExamSortByCategoryDesc(searchContent);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmcglobal.service.ExamService#randomQuestion(java.lang.String)
   * Author: Sanero. Created date: Feb 13, 2019 Created time: 4:17:07 PM
   */
  @Override
  public boolean randomQuestion(String examId, int numberRandom) {
    try {
//    Exam exam = examRepository.findById(examId).get();
      Random random = new Random();
      List<Question> questions = questionService.getAllQuestion();
      List<ExamQuestion> examQuestions = Helper.randomQuestion(random,
          questions, numberRandom, examId);
      for (ExamQuestion examQuestion : examQuestions) {
        examQuestion.setExamId(examId);
        Question question = questionService
            .findById(examQuestion.getQuestion().getQuestionId());
        int countAnswer = question.getAnswers().size();
        String choiceOrder = Helper.randomChoiceOrder(random, countAnswer);
        examQuestion.setChoiceOrder(choiceOrder);
        examQuestionService.insert(examQuestion);
      }
      return true;
    } catch (Exception e) {
      return true;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmcglobal.service.ExamService#removeQuestion(com.cmcglobal.entity.Exam)
   * Author: Sanero. Created date: Feb 13, 2019 Created time: 5:25:05 PM
   */
  @Override
  public boolean removeQuestion(Exam exam) {
    try {
      // Exam updateExam = examRepository.findById(exam.getExamId()).get();
      // updateExam.setExamQuestions(exam.getExamQuestions());
      // updateExam = examRepository.save(updateExam);
      for (ExamQuestion examQuestion : exam.getExamQuestions()) {
        examQuestionService.deleteById(examQuestion.getId());
      }
      return true;
    } catch (Exception e) {
      return true;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmcglobal.service.ExamService#addListQuestion(com.cmcglobal.entity.Exam)
   * Author: Sanero. Created date: Feb 14, 2019 Created time: 8:36:00 AM
   */
  @Override
  public boolean addListQuestion(Exam exam) {
    try {
      String examId = exam.getExamId();
      Random random = new Random();
      for (ExamQuestion examQuestion : exam.getExamQuestions()) {
        examQuestion.setExamId(examId);
        Question question = questionService
            .findById(examQuestion.getQuestion().getQuestionId());
        int countAnswer = question.getAnswers().size();

        String choiceOrder = Helper.randomChoiceOrder(random, countAnswer);
        System.out.println(choiceOrder);
        examQuestion.setChoiceOrder(choiceOrder);
        examQuestionService.insert(examQuestion);
      }
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  @Override
  public String createId() {
    String id;
    List<Exam> exam = examRepository.findAll();
    if (exam.size() == 0)
      id = "exam001";
    else {
      Collections.sort(exam, new Comparator<Exam>() {
        @Override
        public int compare(Exam o1, Exam o2) {
          return o1.getExamId().compareTo(o2.getExamId());
        }
      });

      int ids = exam.size() - 1;
      String tmp = exam.get(ids).getExamId();
      tmp = tmp.substring(tmp.length() - 3, tmp.length());
      int id1 = Integer.parseInt(tmp) + 1;
      if (id1 < 10)
        id = ("Exam00") + id1;
      else if (id1 > 9 && id1 < 100)
        id = ("Exam0") + id1;
      else
        id = ("Exam") + id1;
    }
    return id;
  }

  @Override
  public void deleteExam(String examId) {
    ;
    examRepository.deleteById(examId);

  }

  @Override
  public List<Exam> FilterExam(Exam exam) {
    List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
    return exams;
  }

  public FilterBuilder getFilterBuilder(Exam exam) {
    return new FilterBuilder.Builder()
        .setNumberOfQuestion(exam.getNumberOfQuestion())
        .setDuration(exam.getDuration()).setDateExam(exam.getCreateAt())
        .setStatus(exam.getStatus()).setCaterogyName(exam.getCategoryName())
        .builder();
  }

  @Autowired
  CategoryService categoryService;

  @Override
  public Exam getOne(String examId) {
    // TODO Auto-generated method stub
    return entityManager.find(Exam.class, examId);
  }

  @Override
  public Exam insert(Exam exam) {
    // TODO Auto-generated method stub
    return examRepository.save(exam);
  }

  @Override
  public Exam update(Exam exam) {
    // TODO Auto-generated method stub
    return examRepository.save(exam);
  }

  @Override
  public List<Exam> readExcel(final String exelFilePath) {
    final int COLUMN_INDEX_TITLE = 0;
    final int COLUMN_INDEX_DURATION = 1;
    final int COLUMN_INDEX_CATEGORYID = 2;
    final int COLUMN_INDEX_NOTE = 3;
    final int COLUMN_INDEX_NUMBEROFQUES = 4;

    List<Exam> listExam = new ArrayList<Exam>();
    File file = new File(exelFilePath);
    try {
      FileInputStream fileInput = new FileInputStream(file);

      Workbook workbook = getWorkbook(fileInput, exelFilePath);
      Sheet sheet = workbook.getSheetAt(0);
//			Row rowFirst = sheet.getRow(0);
//			Iterator<Cell> cellrowFirst = rowFirst.cellIterator();

      for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
        Exam exam = new Exam();

        Row row = sheet.getRow(rowNum);
        if (row == null) {
          break;
        }
        Iterator<Cell> cellIt = row.cellIterator();
        while (cellIt.hasNext()) {
          Cell cell = cellIt.next();
          Object cellValue = getCellValue(cell);
          if (cellValue == null || cellValue.toString().isEmpty()) {
            continue;
          }
          System.out.println(cell.toString());

//					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          int columnIndex = cell.getColumnIndex();

          switch (columnIndex) {
          case COLUMN_INDEX_TITLE:
            exam.setTitle((String) getCellValue(cell));
            break;
          case COLUMN_INDEX_DURATION:
            float duration = Float.parseFloat(getCellValue(cell).toString());
            exam.setDuration(duration);
            break;
          case COLUMN_INDEX_CATEGORYID:
            Category category = categoryService
                .getOne(getCellValue(cell).toString());
            exam.setCategory(category);
            break;
          case COLUMN_INDEX_NOTE:
            exam.setNote((String) getCellValue(cell));
            break;
//					case COLUMN_INDEX_STATUS:
//						exam.setStatus((String) getCellValue(cell));
//						break;
          case COLUMN_INDEX_NUMBEROFQUES:
            float x = Float.parseFloat(getCellValue(cell).toString());
            int numberQues = (int) x;
            exam.setNumberOfQuestion(numberQues);
            break;
//					case COLUMN_INDEX_ENABLE:
//						exam.setEnable(Boolean.parseBoolean(getCellValue(cell).toString()));
//						break;
//					case COLUMN_INDEX_CREATE_AT:
//						try {
//							exam.setCreateAt(formatter.parse(getCellValue(cell).toString()));
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//						break;
//					case COLUMN_INDEX_CREATED_BY:
//
//						break;
//					case COLUMN_INDEX_MODIFIED_AT:
//						try {
//							exam.setModifiedAt(formatter.parse((String) getCellValue(cell)));
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//						break;
//					case COLUMN_INDEX_MODIFIED_BY:
//
//						break;
          default:
            break;
          }
        }
        listExam.add(exam);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return listExam;
  }

  // Get Cell's value
  private static Object getCellValue(Cell cell) {
    CellType cellType = cell.getCellTypeEnum();
    Object cellValue = null;
    switch (cellType) {
    case BOOLEAN:
      cellValue = cell.getBooleanCellValue();
      break;
    case FORMULA:
      Workbook workbook = cell.getSheet().getWorkbook();
      FormulaEvaluator evaluator = workbook.getCreationHelper()
          .createFormulaEvaluator();
      cellValue = evaluator.evaluate(cell).getNumberValue();
      break;
    case NUMERIC:
      cellValue = cell.getNumericCellValue();
      break;
    case STRING:
      cellValue = cell.getStringCellValue();
      break;
    case _NONE:
    case BLANK:
    case ERROR:
      break;
    default:
      break;
    }

    return cellValue;
  }

  // Get Workbook
  private static Workbook getWorkbook(InputStream inputStream,
      String excelFilePath) throws IOException {
    Workbook workbook = null;
    if (excelFilePath.endsWith("xlsx")) {
      workbook = new XSSFWorkbook(inputStream);
    } else if (excelFilePath.endsWith("xls")) {
      workbook = new HSSFWorkbook(inputStream);
    } else {
      throw new IllegalArgumentException(
          "The specified file is not Excel file");
    }

    return workbook;
  }

  @Override
  public String createId1() {
    String id;
    List<Exam> findAll = examService.findAll();
    int ids = findAll.size() - 1;
    if (ids < 0) {
      ids = 0;
      return "Exam001";
    } else {
      String tmp = findAll.get(ids).getExamId();
      tmp = tmp.substring(tmp.length() - 3, tmp.length());

      int id1 = Integer.parseInt(tmp) + 1;
      if (id1 < 10)
        id = ("Exam00") + id1;
      else if (id1 > 9 && id1 < 100)
        id = ("Exam0") + id1;
      else
        id = ("Exam") + id1;
      return id;
    }
  }
}
