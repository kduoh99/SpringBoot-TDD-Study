package com.study.spring_boot_unit_testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import com.study.spring_boot_unit_testing.models.CollegeStudent;
import com.study.spring_boot_unit_testing.models.StudentGrades;

@SpringBootTest(classes = SpringBootUnitTestingApplication.class)
public class ReflectionTestUtilsTest {

	@Autowired
	ApplicationContext context;

	@Autowired
	CollegeStudent studentOne;

	@Autowired
	StudentGrades studentGrades;

	@BeforeEach
	public void studentBeforeEach() {
		studentOne.setFirstname("Eric");
		studentOne.setLastname("Roby");
		studentOne.setEmailAddress("eric.roby@luv2code_school.com");
		studentOne.setStudentGrades(studentGrades);

		ReflectionTestUtils.setField(studentOne, "id", 1);
		ReflectionTestUtils.setField(studentOne, "studentGrades",
			new StudentGrades(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75))));
	}

	@Test
	public void getPrivateField() {
		assertEquals(1, ReflectionTestUtils.getField(studentOne, "id"));
	}

	@Test
	public void invokePrivateMethod() {
		assertEquals("Eric 1",
			ReflectionTestUtils.invokeMethod(studentOne, "getFirstNameAndId"),
			"Fail private method not call");
	}
}
