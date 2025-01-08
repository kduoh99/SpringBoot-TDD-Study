package com.study.spring_boot_mvc_testing_demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.study.spring_boot_mvc_testing_demo.models.CollegeStudent;
import com.study.spring_boot_mvc_testing_demo.models.GradebookCollegeStudent;
import com.study.spring_boot_mvc_testing_demo.models.HistoryGrade;
import com.study.spring_boot_mvc_testing_demo.models.MathGrade;
import com.study.spring_boot_mvc_testing_demo.models.ScienceGrade;
import com.study.spring_boot_mvc_testing_demo.repository.HistoryGradeDao;
import com.study.spring_boot_mvc_testing_demo.repository.MathGradeDao;
import com.study.spring_boot_mvc_testing_demo.repository.ScienceGradeDao;
import com.study.spring_boot_mvc_testing_demo.repository.StudentDao;
import com.study.spring_boot_mvc_testing_demo.service.StudentAndGradeService;

@TestPropertySource("/application-test.yml")
@SpringBootTest
public class StudentAndGradeServiceTest {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private StudentAndGradeService studentService;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private MathGradeDao mathGradeDao;

	@Autowired
	private ScienceGradeDao scienceGradeDao;

	@Autowired
	private HistoryGradeDao historyGradeDao;

	@Value("${sql.script.create.student}")
	private String sqlAddStudent;

	@Value("${sql.script.create.grade.math}")
	private String sqlAddMathGrade;

	@Value("${sql.script.create.grade.science}")
	private String sqlAddScienceGrade;

	@Value("${sql.script.create.grade.history}")
	private String sqlAddHistoryGrade;

	@Value("${sql.script.delete.student}")
	private String sqlDeleteStudent;

	@Value("${sql.script.delete.grade.math}")
	private String sqlDeleteMathGrade;

	@Value("${sql.script.delete.grade.science}")
	private String sqlDeleteScienceGrade;

	@Value("${sql.script.delete.grade.history}")
	private String sqlDeleteHistoryGrade;

	@BeforeEach
	public void setupDatabase() {
		jdbc.execute(sqlAddStudent);
		jdbc.execute(sqlAddMathGrade);
		jdbc.execute(sqlAddScienceGrade);
		jdbc.execute(sqlAddHistoryGrade);
	}

	@Test
	public void createStudentService() {

		studentService.createStudent("Chad", "Darby", "chad.darby@luv2code_school.com");

		CollegeStudent student = studentDao.findByEmailAddress("chad.darby@luv2code_school.com");

		assertEquals("chad.darby@luv2code_school.com", student.getEmailAddress(), "find by email");
	}

	@Test
	public void isStudentNullCheck() {

		assertTrue(studentService.checkIfStudentIsNull(1));

		assertFalse(studentService.checkIfStudentIsNull(0));
	}

	@Test
	public void deleteStudentService() {

		Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
		Optional<MathGrade> deletedMathGrade = mathGradeDao.findById(1);
		Optional<ScienceGrade> deletedScienceGrade = scienceGradeDao.findById(1);
		Optional<HistoryGrade> deletedHistoryGrade = historyGradeDao.findById(1);

		assertTrue(deletedCollegeStudent.isPresent(), "Return True");
		assertTrue(deletedMathGrade.isPresent());
		assertTrue(deletedScienceGrade.isPresent());
		assertTrue(deletedHistoryGrade.isPresent());

		studentService.deleteStudent(1);

		deletedCollegeStudent = studentDao.findById(1);
		deletedMathGrade = mathGradeDao.findById(1);
		deletedScienceGrade = scienceGradeDao.findById(1);
		deletedHistoryGrade = historyGradeDao.findById(1);

		assertFalse(deletedCollegeStudent.isPresent(), "Return False");
		assertFalse(deletedMathGrade.isPresent());
		assertFalse(deletedScienceGrade.isPresent());
		assertFalse(deletedHistoryGrade.isPresent());
	}

	@Sql("/insertData.sql")
	@Test
	public void getGradebookService() {
		Iterable<CollegeStudent> iterableCollegeStudents = studentService.getGradebook();

		List<CollegeStudent> collegeStudents = new ArrayList<>();

		for (CollegeStudent collegeStudent : iterableCollegeStudents) {
			collegeStudents.add(collegeStudent);
		}

		assertEquals(5, collegeStudents.size());
	}

	@Test
	public void createGradeService() {

		// Create the grade
		assertTrue(studentService.createGrade(80.50, 1, "math"));
		assertTrue(studentService.createGrade(80.50, 1, "science"));
		assertTrue(studentService.createGrade(80.50, 1, "history"));

		// Get all grades with studentId
		Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(1);
		Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(1);
		Iterable<HistoryGrade> historyGrades = historyGradeDao.findGradeByStudentId(1);

		// Verify there is grades
		assertTrue(((Collection<MathGrade>)mathGrades).size() == 2, "Student has math grades");
		assertTrue(((Collection<ScienceGrade>)scienceGrades).size() == 2);
		assertTrue(((Collection<HistoryGrade>)historyGrades).size() == 2);

	}

	@Test
	public void createGradeServiceReturnFalse() {
		assertFalse(studentService.createGrade(105, 1, "math"));
		assertFalse(studentService.createGrade(-5, 1, "math"));
		assertFalse(studentService.createGrade(80.50, 2, "math"));
		assertFalse(studentService.createGrade(80.50, 1, "literature"));
	}

	@Test
	public void deleteGradeService() {
		assertEquals(1, studentService.deleteGrade(1, "math"), "Return student id after delete");
		assertEquals(1, studentService.deleteGrade(1, "science"), "Return student id after delete");
		assertEquals(1, studentService.deleteGrade(1, "history"), "Return student id after delete");
	}

	@Test
	public void deleteGradeServiceReturnStudentIdOfZero() {
		assertEquals(0, studentService.deleteGrade(0, "science"), "No student should have 0 id");
		assertEquals(0, studentService.deleteGrade(1, "literature"), "No student should have a literature class");
	}

	@Test
	public void studentInformation() {

		GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(1);

		assertNotNull(gradebookCollegeStudent);
		assertEquals(1, gradebookCollegeStudent.getId());
		assertEquals("Eric", gradebookCollegeStudent.getFirstname());
		assertEquals("Roby", gradebookCollegeStudent.getLastname());
		assertEquals("eric.roby@luv2code_school.com", gradebookCollegeStudent.getEmailAddress());
		assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size() == 1);
		assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size() == 1);
		assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size() == 1);
	}

	@Test
	public void studentInformationServiceReturnNull() {

		GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(0);

		assertNull(gradebookCollegeStudent);
	}

	@AfterEach
	public void setupAfterTransaction() {
		jdbc.execute(sqlDeleteStudent);
		jdbc.execute(sqlDeleteMathGrade);
		jdbc.execute(sqlDeleteScienceGrade);
		jdbc.execute(sqlDeleteHistoryGrade);
	}
}
