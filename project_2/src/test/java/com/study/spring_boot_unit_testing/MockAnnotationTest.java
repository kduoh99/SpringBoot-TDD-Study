package com.study.spring_boot_unit_testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.study.spring_boot_unit_testing.dao.ApplicationDao;
import com.study.spring_boot_unit_testing.models.CollegeStudent;
import com.study.spring_boot_unit_testing.models.StudentGrades;
import com.study.spring_boot_unit_testing.service.ApplicationService;

@SpringBootTest(classes = SpringBootUnitTestingApplication.class)
public class MockAnnotationTest {

	@Autowired
	ApplicationContext context;

	@Autowired
	CollegeStudent studentOne;

	@Autowired
	StudentGrades studentGrades;

	// @Mock
	@MockitoBean  // Spring Boot 3.4.0 버전부터 MockBean이 deprecated됨
	private ApplicationDao applicationDao;

	// @InjectMocks
	@Autowired
	private ApplicationService applicationService;

	@BeforeEach
	public void beforeEach() {
		studentOne.setFirstname("Eric");
		studentOne.setLastname("Roby");
		studentOne.setEmailAddress("eric.roby@luv2code_school.com");
		studentOne.setStudentGrades(studentGrades);
	}

	@DisplayName("When & Verify")
	@Test
	public void assertEqualsTestAddGrades() {
		when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(100.00);

		assertEquals(100,
			applicationService.addGradeResultsForSingleClass(studentOne.getStudentGrades().getMathGradeResults()));

		verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());

		verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
	}

	@DisplayName("Find Gpa")
	@Test
	public void assertEqualsTestFindGpa() {
		when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults()))
			.thenReturn(88.31);

		assertEquals(88.31,
			applicationService.findGradePointAverage(studentOne.getStudentGrades().getMathGradeResults()));
	}

	@DisplayName("Not Null")
	@Test
	public void testAssertNotNull() {
		when(applicationDao.checkNull(studentGrades.getMathGradeResults())).thenReturn(true);

		assertNotNull(applicationService.checkNull(studentOne.getStudentGrades().getMathGradeResults()), "Object should not be null");
	}

	@DisplayName("Throw runtime error")
	@Test
	public void throwRuntimeError() {
		CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");

		doThrow(new RuntimeException()).when(applicationDao).checkNull(nullStudent);

		assertThrows(RuntimeException.class, () -> {
			applicationService.checkNull(nullStudent);
		});

		verify(applicationDao, times(1)).checkNull(nullStudent);
	}

	@DisplayName("Multiple Stubbing")
	@Test
	public void stubbingConsecutiveCalls() {
		CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");

		when(applicationDao.checkNull(nullStudent))
			.thenThrow(new RuntimeException())
			.thenReturn("Do not throw exception second time");

		assertThrows(RuntimeException.class, () -> {
			applicationService.checkNull(nullStudent);
		});

		assertEquals("Do not throw exception second time",
			applicationService.checkNull(nullStudent));

		verify(applicationDao, times(2)).checkNull(nullStudent);
	}
}
