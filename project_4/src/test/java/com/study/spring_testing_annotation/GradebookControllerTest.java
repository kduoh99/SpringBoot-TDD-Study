package com.study.spring_testing_annotation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring_testing_annotation.models.CollegeStudent;
import com.study.spring_testing_annotation.repository.HistoryGradesDao;
import com.study.spring_testing_annotation.repository.MathGradesDao;
import com.study.spring_testing_annotation.repository.ScienceGradesDao;
import com.study.spring_testing_annotation.repository.StudentDao;
import com.study.spring_testing_annotation.service.StudentAndGradeService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@TestPropertySource("/application-test.yml")
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional
public class GradebookControllerTest {

	private static MockHttpServletRequest request;

	@PersistenceContext
	private EntityManager em;

	@Mock
	StudentAndGradeService studentCreateServiceMock;

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private MathGradesDao mathGradeDao;

	@Autowired
	private ScienceGradesDao scienceGradeDao;

	@Autowired
	private HistoryGradesDao historyGradeDao;

	@Autowired
	private StudentAndGradeService studentService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private CollegeStudent student;

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

	public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

	@BeforeAll
	public static void setup() {
		request = new MockHttpServletRequest();
		request.setParameter("firstname", "Chad");
		request.setParameter("lastname", "Darby");
		request.setParameter("emailAddress", "chad.darby@luv2code_school.com");
	}

	@BeforeEach
	public void setupDatabase() {
		jdbc.execute(sqlAddStudent);
		jdbc.execute(sqlAddMathGrade);
		jdbc.execute(sqlAddScienceGrade);
		jdbc.execute(sqlAddHistoryGrade);
	}

	@AfterEach
	public void setupAfterTransaction() {
		jdbc.execute(sqlDeleteStudent);
		jdbc.execute(sqlDeleteMathGrade);
		jdbc.execute(sqlDeleteScienceGrade);
		jdbc.execute(sqlDeleteHistoryGrade);
	}
}
