package com.cmcglobal.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
import com.cmcglobal.service.UploadFileService;
import com.cmcglobal.utils.Constants;
import com.cmcglobal.utils.Helper;
import com.cmcglobal.utils.MyException;

/*
 * @author Sanero.
 * Created date: Feb 22, 2019
 * Created time: 2:10:49 PM
 * Description: TODO - Exam service implementation.
 */
@Service
@Transactional
public class ExamServiceImpl implements ExamService {
	static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

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

	@Autowired
	UploadFileService uploadFileService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#createExam(com.cmcglobal.entity.Exam)
	 * Author: ptphuong. Created date: Feb 22, 2019 Created time: 2:11:47 PM
	 */
	@Override
	public void createExam(Exam ex) {
		try {
			User user = new User();
			user.setUserId(1);
			ex.setExamId(this.createId());
			ex.setTitle(ex.getTitle().trim());
			ex.setNote(ex.getNote().substring(3, ex.getNote().length() - 4));
			ex.setUserCreated(user);
			// ex.setCreateAt(new Date());
			ex.setEnable(true);
			examRepository.save(ex);
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.CREATE_FAILED + exception.getClass() + " - " + exception.getMessage());
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.CREATE_FAILED + e.getClass() + " - " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#findAll() Author: ntmduyen. Created
	 * date: Feb 22, 2019 Created time: 2:12:30 PM
	 */
	@Override
	public List<Exam> findAll() {
		try {
			return examRepository.findAll();
		} catch (NoResultException exception) {
			LOGGER.error(Constants.Exam.GET_ALL_FAILED + exception.getClass() + " - " + exception.getMessage());
			return null;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.GET_ALL_FAILED + exception.getClass() + " - " + exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_ALL_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#findByID(java.lang.String) Author:
	 * vvdong. Created date: Feb 22, 2019 Created time: 2:12:39 PM
	 */
	@Override
	public Exam findById(String id) {
		try {
			return examRepository.findById(id).get();
		} catch (EntityNotFoundException exception) {
			LOGGER.error(Constants.Exam.GET_ONE_FAILED + exception.getClass() + " - " + exception.getMessage());
			return null;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.GET_ONE_FAILED + exception.getClass() + " - " + exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_ONE_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
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
			if (exam.getExamQuestions().size() == 0) {
				return false;
			}
			exam.setStatus(Constants.Exam.STATUS_PUBLIC);
			exam = examRepository.save(exam);
			if (Constants.Exam.STATUS_PUBLIC.equals(exam.getStatus())) {
				return true;
			}
			return false;
		} catch (NoSuchElementException exception) {
			LOGGER.error(Constants.Exam.APPROVE_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.APPROVE_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.APPROVE_FAILED + e.getClass() + " - " + e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#pageExam (java.lang.String,
	 * org.springframework.data.domain.Sort) Author: ntmduyen. Created date: Feb 22,
	 * 2019 Created time: 2:12:50 PM
	 */
	@Override
	public List<Exam> pageExam(String searchContent, Sort pageable) {
		try {
			return examRepository.pageExam(searchContent, pageable);
		} catch (NoResultException exception) {
			LOGGER.error(Constants.Exam.GET_PAGE_EXAM_FAILED + exception.getClass() + " - " + exception.getMessage());
			return null;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.GET_PAGE_EXAM_FAILED + exception.getClass() + " - " + exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_PAGE_EXAM_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cmcglobal.service.ExamService#pageExamSortByUserCreatedByAsc(java.lang.
	 * String) Author: ntmduyen. Created date: Feb 22, 2019 Created time: 2:12:59 PM
	 */
	@Override
	public List<Exam> pageExamSortByUserCreatedByAsc(String searchContent) {
		try {
			return examRepository.pageExamSortByUserCreatedByAsc(searchContent);
		} catch (NoResultException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_USER_ASC_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_USER_ASC_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_USER_ASC_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cmcglobal.service.ExamService#pageExamSortByUserCreatedByDesc(java.lang.
	 * String) Author: ntmduyen. Created date: Feb 22, 2019 Created time: 2:13:03 PM
	 */
	@Override
	public List<Exam> pageExamSortByUserCreatedByDesc(String searchContent) {
		try {
			return examRepository.pageExamSortByUserCreatedByDesc(searchContent);
		} catch (NoResultException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_USER_DES_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_USER_DES_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_USER_DES_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cmcglobal.service.ExamService#pageExamSortByCategoryAsc(java.lang.String)
	 * Author: ntmduyen. Created date: Feb 22, 2019 Created time: 2:13:08 PM
	 */
	@Override
	public List<Exam> pageExamSortByCategoryAsc(String searchContent) {
		try {
			return examRepository.pageExamSortByCategoryAsc(searchContent);
		} catch (NoResultException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_CATEGORY_ASC_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_CATEGORY_ASC_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_CATEGORY_ASC_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#pageExamSortByCategoryDesc(java.lang.
	 * String) Author: ntmduyen. Created date: Feb 22, 2019 Created time: 2:13:12 PM
	 */
	@Override
	public List<Exam> pageExamSortByCategoryDesc(String searchContent) {
		try {
			return examRepository.pageExamSortByCategoryDesc(searchContent);
		} catch (NoResultException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_CATEGORY_DES_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_CATEGORY_DES_FAILED + exception.getClass() + " - "
					+ exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_EXAM_SORT_BY_CATEGORY_DES_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#randomQuestion(java.lang.String)
	 * Author: Sanero. Created date: Feb 13, 2019 Created time: 4:17:07 PM
	 */
	@Override
	public boolean randomQuestion(String examId, int categoryId, int numberRandom) {
		try {
			// Exam exam = examRepository.findById(examId).get();
			Random random = new Random();
			List<Question> questions = questionService.findByCategoryId(categoryId);
			List<ExamQuestion> examQuestions = Helper.randomQuestion(random, questions, numberRandom, examId);
			if (examQuestions.size() == 0) {
				return false;
			}
			for (ExamQuestion examQuestion : examQuestions) {
				examQuestion.setExamId(examId);
				Question question = questionService.findById(examQuestion.getQuestion().getQuestionId());
				int countAnswer = question.getAnswers().size();
				String choiceOrder = Helper.randomChoiceOrder(random, countAnswer);
				examQuestion.setChoiceOrder(choiceOrder);
				examQuestionService.insert(examQuestion);
			}
			return true;
		} catch (NoResultException exception) {
			LOGGER.error(Constants.Exam.RANDOM_QUESTION_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (NoSuchElementException exception) {
			LOGGER.error(Constants.Exam.RANDOM_QUESTION_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.RANDOM_QUESTION_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.RANDOM_QUESTION_FAILED + e.getClass() + " - " + e.getMessage());
			return false;
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
		} catch (NoSuchElementException exception) {
			LOGGER.error(Constants.Exam.REMOVE_QUESTION_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.REMOVE_QUESTION_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.REMOVE_QUESTION_FAILED + e.getClass() + " - " + e.getMessage());
			return false;
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
				Question question = questionService.findById(examQuestion.getQuestion().getQuestionId());
				int countAnswer = question.getAnswers().size();

				String choiceOrder = Helper.randomChoiceOrder(random, countAnswer);
				System.out.println(choiceOrder);
				examQuestion.setChoiceOrder(choiceOrder);
				examQuestionService.insert(examQuestion);
			}
			return true;
		} catch (NoSuchElementException exception) {
			LOGGER.error(Constants.Exam.ADD_QUESTION_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.ADD_QUESTION_FAILED + exception.getClass() + " - " + exception.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.ADD_QUESTION_FAILED + e.getClass() + " - " + e.getMessage());
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#createId() Author: ptphuong. Created
	 * date: Feb 22, 2019 Created time: 2:13:31 PM
	 */
	@Override
	public String createId() {
		try {
			String id;
			List<Exam> exam = examRepository.findAll();
			if (exam.size() == 0) {
				id = Constants.Exam.ID_START;
			} else {
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
				if (id1 < 10) {
					id = (Constants.Exam.ID_NINE) + id1;
				} else if (id1 > 9 && id1 < 100) {
					id = (Constants.Exam.ID_DECADE) + id1;
				} else {
					id = (Constants.Exam.ID_HUNDERED) + id1;
				}
			}
			return id;
		} catch (NoResultException exception) {
			LOGGER.error(
					Constants.Exam.GENERATE_EXAM_ID_FAILED + exception.getClass() + " - " + exception.getMessage());
			return Constants.Exam.ID_START;
		} catch (PersistenceException exception) {
			LOGGER.error(
					Constants.Exam.GENERATE_EXAM_ID_FAILED + exception.getClass() + " - " + exception.getMessage());
			return Constants.Exam.ID_START;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GENERATE_EXAM_ID_FAILED + e.getClass() + " - " + e.getMessage());
			return Constants.Exam.ID_START;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#deleteExam(java.lang.String) Author:
	 * ndvan. Created date: Feb 22, 2019 Created time: 2:14:02 PM
	 */
	@Override
	public void deleteExam(String examId) {
		try {
			examRepository.deleteById(examId);
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.DELETE_EXAM_FAILED + e.getClass() + " - " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#FilterExam(com.cmcglobal.entity.Exam)
	 * Author: ndvan. Created date: Feb 22, 2019 Created time: 2:14:08 PM
	 */
	@Override
	public List<Exam> filterExam(Exam exam) {
		try {
			List<Exam> exams = examRepository.findAll(getFilterBuilder(exam));
			return exams;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.FILTER_EXAM_FAILED + e.getClass() + " - " + e.getMessage());
			return new ArrayList<Exam>();
		}
	}

	/**
	 * Author: ndvan. Created date: Feb 22, 2019 Created time: 2:14:15 PM
	 * Description: TODO - .
	 * 
	 * @param exam - exam.
	 * @return
	 */
	public FilterBuilder getFilterBuilder(Exam exam) {
		return new FilterBuilder.Builder().setNumberOfQuestion(exam.getNumberOfQuestion())
				.setDuration(exam.getDuration()).setStatus(exam.getStatus()).setCaterogyName(exam.getCategoryName())
				.setCreateAt(exam.getCreateAt()).builder();
	}

	@Autowired
	CategoryService categoryService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#getOne(java.lang.String) Author:
	 * hai95. Created date: Feb 22, 2019 Created time: 2:14:29 PM
	 */
	@Override
	public Exam getOne(String examId) {
		try {
			// TODO Auto-generated method stub
			return entityManager.find(Exam.class, examId);
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.GET_ONE_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#insert(com.cmcglobal.entity.Exam)
	 * Author: hai95. Created date: Feb 22, 2019 Created time: 2:14:41 PM
	 */
	@Override
	public Exam insert(Exam exam) {
		try {
			// TODO Auto-generated method stub
			return examRepository.save(exam);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(Constants.Exam.INSERT_EXAM_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#update(com.cmcglobal.entity.Exam)
	 * Author: hai95. Created date: Feb 22, 2019 Created time: 2:14:52 PM
	 */
	@Override
	public Exam update(Exam exam) {
		try {
			// TODO Auto-generated method stub
			return examRepository.save(exam);
		} catch (PersistenceException exception) {
			LOGGER.error(Constants.Exam.UPDATE_EXAM_FAILED + exception.getClass() + " - " + exception.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(Constants.Exam.UPDATE_EXAM_FAILED + e.getClass() + " - " + e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#readExcel(java.lang.String) Author:
	 * ahi95. Created date: Feb 22, 2019 Created time: 2:14:59 PM
	 */
	@Override
	public List<Exam> readExcel(final String exelFilePath) throws Exception {
		List<Exam> listExam = new ArrayList<Exam>();
		File file = new File(exelFilePath);
		try {
			FileInputStream fileInput = new FileInputStream(file);

			Workbook workbook = getWorkbook(fileInput, exelFilePath);
			Sheet sheet = workbook.getSheetAt(0);

			Row rowFirst = sheet.getRow(0);
			if (checkIfRowIsEmpty(rowFirst)) {
				throw new MyException(1, "this excel file is empty");
			}

			if (!(examService.checkNotFormatedFile(rowFirst))) {
				throw new MyException(2, "this excel file is not formatted with one's template");
			}

			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Exam exam = new Exam();

				Row row = sheet.getRow(rowNum);
				if (checkIfRowIsEmpty(row)) {
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

					// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					int columnIndex = cell.getColumnIndex();

					switch (columnIndex) {
					case Constants.ExcelTemplate.COLUMN_INDEX_TITLE:
						exam.setTitle((String) getCellValue(cell));
						break;
					case Constants.ExcelTemplate.COLUMN_INDEX_DURATION:
						float duration = Float.parseFloat(getCellValue(cell).toString());
						exam.setDuration(duration);
						break;
					case Constants.ExcelTemplate.COLUMN_INDEX_CATEGORYID:
						Category category = categoryService.getOne(getCellValue(cell).toString());
						exam.setCategory(category);
						break;
					case Constants.ExcelTemplate.COLUMN_INDEX_NOTE:
						exam.setNote((String) getCellValue(cell));
						break;
					// case COLUMN_INDEX_STATUS:
					// exam.setStatus((String) getCellValue(cell));
					// break;
					case Constants.ExcelTemplate.COLUMN_INDEX_NUMBEROFQUES:
						float x = Float.parseFloat(getCellValue(cell).toString());
						int numberQues = (int) x;
						exam.setNumberOfQuestion(numberQues);
						break;
					// case COLUMN_INDEX_ENABLE:
					// exam.setEnable(Boolean.parseBoolean(getCellValue(cell).toString()));
					// break;
					// case COLUMN_INDEX_CREATE_AT:
					// try {
					// exam.setCreateAt(formatter.parse(getCellValue(cell).toString()));
					// } catch (ParseException e) {
					// e.printStackTrace();
					// }
					// break;
					// case COLUMN_INDEX_CREATED_BY:
					//
					// break;
					// case COLUMN_INDEX_MODIFIED_AT:
					// try {
					// exam.setModifiedAt(formatter.parse((String) getCellValue(cell)));
					// } catch (ParseException e) {
					// e.printStackTrace();
					// }
					// break;
					// case COLUMN_INDEX_MODIFIED_BY:
					//
					// break;
					default:
						break;
					}
				}
				User user = new User();
				user.setUserId(1);
				exam.setUserCreated(user);
				exam.setStatus("Draft");
				// exam.setCreateAt(new Date());
				listExam.add(exam);
			}
		} catch (IOException e) {
			LOGGER.error(e.toString());
			e.printStackTrace();
		}
		return listExam;
	}

	// check if row of excel file is empty
	private static boolean checkIfRowIsEmpty(Row row) {
		if (row == null || row.getLastCellNum() <= 0) {
			return true;
		}
		Cell cell = row.getCell((int) row.getFirstCellNum());
		if (cell == null || "".equals(cell.getRichStringCellValue().getString())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkNotFormatedFile(Row row) {
		boolean checkCell0 = Constants.ExcelTemplate.COLUMN_0.equals(row.getCell(0).toString()) ? true : false;
		boolean checkCell1 = Constants.ExcelTemplate.COLUMN_1.equals(row.getCell(1).toString()) ? true : false;
		boolean checkCell2 = Constants.ExcelTemplate.COLUMN_2.equals(row.getCell(2).toString()) ? true : false;
		boolean checkCell3 = Constants.ExcelTemplate.COLUMN_3.equals(row.getCell(3).toString()) ? true : false;
		boolean checkCell4 = Constants.ExcelTemplate.COLUMN_4.equals(row.getCell(4).toString()) ? true : false;
		if (!(checkCell0 && checkCell1 && checkCell2 && checkCell3 && checkCell4)) {
			return false;
		}
		return true;
	}

	/**
	 * Author: hai95. Created date: Feb 22, 2019 Created time: 2:15:16 PM
	 * Description: TODO - .
	 * 
	 * @param cell - cell.
	 * @return
	 */
	private static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellTypeEnum();
		Object cellValue = null;
		switch (cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case FORMULA:
			Workbook workbook = cell.getSheet().getWorkbook();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
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
	/**
	 * Author: Sanero. Created date: Feb 22, 2019 Created time: 2:15:25 PM
	 * Description: TODO - hai95.
	 * 
	 * @param inputStream   - input stream.
	 * @param excelFilePath - excel path file.
	 * @return
	 */
	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cmcglobal.service.ExamService#isEmptyQuestionOfExam(java.lang.String)
	 * Author: Sanero. Created date: Feb 22, 2019 Created time: 11:14:11 AM
	 */
	@Override
	public boolean isEmptyQuestionOfExam(String examId) {
		try {
			Exam exam = examRepository.findById(examId).get();
			if (exam.getExamQuestions().size() == 0) {
				exam.setStatus("Draft");
				exam = examRepository.save(exam);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#getListExamByPage (java.lang.String,
	 * java.lang.String, java.lang.String) Author: ntmduyen. Created date: Feb 23,
	 * 2019 Created time: 5:22:43 PM
	 */
	@Override
	public List<Exam> getListExamByPage(String sortOrder, String sortTerm, String searchContent) {
		Sort sortable = null;
		switch (sortTerm) {
		case (Constants.Exam.TITLE): {
			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.TITLE).ascending();
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.TITLE).descending();
			}
			break;
		}
		case (Constants.Exam.EXAM_ID): {
			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.EXAM_ID).ascending();
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.EXAM_ID).descending();
			}
			break;
		}
		case (Constants.Exam.DURATION): {
			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.DURATION).ascending();
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.DURATION).descending();
			}
			break;
		}
		case (Constants.Exam.NUMBER_OF_QUESTION): {
			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.NUMBER_OF_QUESTION).ascending();
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.NUMBER_OF_QUESTION).descending();
			}
			break;
		}
		case (Constants.Exam.STATUS): {
			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.STATUS).ascending();
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.STATUS).descending();
			}
			break;
		}
		case (Constants.Exam.CREATED_AT): {
			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.CREATED_AT).ascending();
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				sortable = Sort.by(Constants.Exam.CREATED_AT).descending();
			}
			break;
		}
		// sort theo trường fullname của user create_by
		case (Constants.Exam.USER_CREATED): {
			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				return pageExamSortByUserCreatedByAsc(searchContent);
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				return pageExamSortByUserCreatedByDesc(searchContent);
			}
			break;
		}
		// sort theo trường category của category category_name
		case (Constants.Exam.CATEGORY): {

			if ((Constants.Exam.ASC).equals(sortOrder.toLowerCase())) {
				return pageExamSortByCategoryAsc(searchContent);
			}
			if ((Constants.Exam.DESC).equals(sortOrder.toLowerCase())) {
				return pageExamSortByCategoryDesc(searchContent);
			}
			break;
		}
		default:
			break;
		}
		return pageExam(searchContent, sortable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmcglobal.service.ExamService#readFileExcel(
	 * org.springframework.web.multipart.MultipartFile) Author: hai95. Created date:
	 * Feb 23, 2019 Created time: 6:03:36 PM
	 */
	@Override
	public ResponseEntity<String> readFileExcel(MultipartFile multipartFile) {
		final String pathFile = uploadFileService.getPathFile(multipartFile);
		System.out.println(pathFile);
		List<Exam> listExam = new ArrayList<>();
		try {
			listExam = examService.readExcel(pathFile);
		} catch (MyException e) {
			System.out.println("Error: " + e.getMessException());
			LOGGER.info(e.getIdException() + ": " + e.getMessException());
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessException());
		} catch (Exception e1) {
			LOGGER.info(e1.toString());
			return ResponseEntity.status(HttpStatus.OK).body(Constants.Exam.NOT_OK);
		}
		for (Exam exam : listExam) {
			exam.setExamId(examService.createId());
			examService.insert(exam);
		}
		return ResponseEntity.status(HttpStatus.OK).body(Constants.Exam.OK);
	}
}