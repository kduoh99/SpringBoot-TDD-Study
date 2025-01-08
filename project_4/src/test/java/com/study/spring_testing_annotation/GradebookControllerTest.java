package com.study.spring_testing_annotation;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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

	@Test
	public void getStudentsHttpRequest() throws Exception {

		student.setFirstname("Chad");
		student.setLastname("Darby");
		student.setEmailAddress("chad.darby@luv2code_school.com");
		em.persist(student);
		em.flush();

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void createStudentHttpRequest() throws Exception {

		student.setFirstname("Chad");
		student.setLastname("Darby");
		student.setEmailAddress("chad_darby@luv2code_school.com");

		mockMvc.perform(MockMvcRequestBuilders.post("/")
				.contentType(APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(student)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)));

		CollegeStudent verifyStudent = studentDao.findByEmailAddress("chad_darby@luv2code_school.com");
		assertNotNull(verifyStudent, "Student should be valid");
	}

	@Test
	public void deleteStudentHttpRequest() throws Exception {

		assertTrue(studentDao.findById(1).isPresent());

		mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void deleteStudentHttpRequestErrorPage() throws Exception {

		assertFalse(studentDao.findById(0).isPresent());

		mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", 0))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.status", is(404)))
			.andExpect(jsonPath("$.message", is("Student or Grade was not found")));
	}

	@Test
	public void studentInformationHttpRequest() throws Exception {

		Optional<CollegeStudent> student = studentDao.findById(1);

		assertTrue(student.isPresent());

		mockMvc.perform(MockMvcRequestBuilders.get("/studentInformation/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstname", is("Eric")))
			.andExpect(jsonPath("$.lastname", is("Roby")))
			.andExpect(jsonPath("$.emailAddress", is("eric.roby@luv2code_school.com")));
	}

	@Test
	public void studentInformationRequestEmptyResponse() throws Exception {

		Optional<CollegeStudent> student = studentDao.findById(0);

		assertFalse(student.isPresent());

		mockMvc.perform(MockMvcRequestBuilders.get("/studentInformation/{id}", 0))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.status", is(404)))
			.andExpect(jsonPath("$.message", is("Student or Grade was not found")));
	}

	@Test
	public void createAValidGradeHttpRequest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/grades")
				.contentType(APPLICATION_JSON_UTF8)
				.param("grade", "85.00")
				.param("gradeType", "math")
				.param("studentId", "1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstname", is("Eric")))
			.andExpect(jsonPath("$.lastname", is("Roby")))
			.andExpect(jsonPath("$.emailAddress", is("eric.roby@luv2code_school.com")))
			.andExpect(jsonPath("$.studentGrades.mathGradeResults", hasSize(2)));
	}

	@Test
	public void createAValidGradeHttpRequestStudentDoesNotExistEmptyResponse() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/grades")
				.contentType(APPLICATION_JSON_UTF8)
				.param("grade", "85.00")
				.param("gradeType", "math")
				.param("studentId", "0"))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.status", is(404)))
			.andExpect(jsonPath("$.message", is("Student or Grade was not found")));
	}

	@AfterEach
	public void setupAfterTransaction() {
		jdbc.execute(sqlDeleteStudent);
		jdbc.execute(sqlDeleteMathGrade);
		jdbc.execute(sqlDeleteScienceGrade);
		jdbc.execute(sqlDeleteHistoryGrade);
	}
}
