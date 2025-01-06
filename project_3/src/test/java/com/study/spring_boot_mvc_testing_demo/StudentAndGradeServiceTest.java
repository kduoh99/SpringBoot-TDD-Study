package com.study.spring_boot_mvc_testing_demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.study.spring_boot_mvc_testing_demo.models.CollegeStudent;
import com.study.spring_boot_mvc_testing_demo.repository.StudentDao;
import com.study.spring_boot_mvc_testing_demo.service.StudentAndGradeService;

@TestPropertySource("/application.yml")
@SpringBootTest
public class StudentAndGradeServiceTest {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private StudentAndGradeService studentService;

	@Autowired
	private StudentDao studentDao;

	@BeforeEach
	public void setupDatabase() {
		jdbc.execute("insert into student(id, firstname, lastname, email_address) " +
			"values (1, 'Eric', 'Roby', 'eric.roby@luv2code_school.com')");
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

		assertTrue(deletedCollegeStudent.isPresent(), "Return True");

		studentService.deleteStudent(1);

		deletedCollegeStudent = studentDao.findById(1);

		assertFalse(deletedCollegeStudent.isPresent(), "Return False");
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

	@AfterEach
	public void setupAfterTransaction() {
		jdbc.execute("DELETE FROM student");
	}
}
