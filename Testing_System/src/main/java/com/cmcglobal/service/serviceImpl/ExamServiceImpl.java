package com.cmcglobal.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Category;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.repository.ExamRepository;
import com.cmcglobal.service.CategoryService;
import com.cmcglobal.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	EntityManager entityManager;

	@Autowired
	ExamRepository examRepository;

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
		return entityManager.merge(exam);
	}

	@Override
	public List<Exam> readExcel(final String exelFilePath) {
//		https://stackoverflow.com/questions/40214772/file-upload-in-angular
		final int COLUMN_INDEX_TITLE = 0;
		final int COLUMN_INDEX_DURATION = 1;
		final int COLUMN_INDEX_CATEGORYID = 2;
		final int COLUMN_INDEX_NOTE = 3;
		final int COLUMN_INDEX_STATUS = 4;
		final int COLUMN_INDEX_ENABLE = 5;
		final int COLUMN_INDEX_NUMBEROFQUES = 6;
		final int COLUMN_INDEX_CREATE_AT = 7;
		final int COLUMN_INDEX_CREATED_BY = 8;
		final int COLUMN_INDEX_MODIFIED_AT = 9;
		final int COLUMN_INDEX_MODIFIED_BY = 10;

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

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					int columnIndex = cell.getColumnIndex();

					switch (columnIndex) {
					case COLUMN_INDEX_TITLE:
						exam.setTitle((String) getCellValue(cell));
						;
						break;
					case COLUMN_INDEX_DURATION:
						float duration = Float.parseFloat(getCellValue(cell).toString());
						exam.setDuration(duration);
						break;
					case COLUMN_INDEX_CATEGORYID:
						float catergoryCell = Float.parseFloat(getCellValue(cell).toString());
						int categoryId = (int) catergoryCell;
						Category category = categoryService.getOneById(categoryId);
						exam.setCategory(category);
						break;
					case COLUMN_INDEX_NOTE:
						exam.setNote((String) getCellValue(cell));
						break;
					case COLUMN_INDEX_STATUS:
						exam.setStatus((String) getCellValue(cell));
						break;
					case COLUMN_INDEX_NUMBEROFQUES:
						float x = Float.parseFloat(getCellValue(cell).toString());
						int numberQues = (int) x;
						exam.setNumberOfQuestion(numberQues);
						break;
					case COLUMN_INDEX_ENABLE:
						exam.setEnable(Boolean.parseBoolean(getCellValue(cell).toString()));
						break;
					case COLUMN_INDEX_CREATE_AT:
						try {
							exam.setCreateAt(formatter.parse(getCellValue(cell).toString()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case COLUMN_INDEX_CREATED_BY:

						break;
					case COLUMN_INDEX_MODIFIED_AT:
						try {
							exam.setModifiedAt(formatter.parse((String) getCellValue(cell)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case COLUMN_INDEX_MODIFIED_BY:

						break;
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

	@Override
	public String createId() {
		String id;
		List<Exam> exam = examRepository.findAll();
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
		return id;
	}

}
