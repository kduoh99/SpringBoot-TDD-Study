package com.study.springboot_tdd;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

	DemoUtils demoUtils;

	@BeforeEach
	void setupBeforeEach() {
		demoUtils = new DemoUtils();
		System.out.println("@BeforeEach executes before the execution of each test method");
	}

	@AfterEach
	void tearDownAfterEach() {
		System.out.println("Running @AfterEach");
		System.out.println();
	}

	@BeforeAll
	static void setupBeforeEachClass() {
		System.out.println("@BeforeAll executes only once before all test methods execution in the class");
	}

	@AfterAll
	static void tearDownAfterAll() {
		System.out.println("@AfterAll executes only once after all test methods execution in the class");
	}

	@Test
	void testEqualsAndNotEquals() {

		System.out.println("Running test: testEqualsAndNotEquals");

		assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
		assertNotEquals(8, demoUtils.add(1, 9), "1+9 must not be 8");
	}

	@Test
	void testNullAndNotNull() {

		System.out.println("Running test: testNullAndNotNull");

		String str1 = null;
		String str2 = "duoh";

		assertNull(demoUtils.checkNull(str1), "Object should be null");
		assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
	}
}
