package com.study.spring_boot_unit_testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

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

	@Mock
	private ApplicationDao applicationDao;

	@InjectMocks
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
}
