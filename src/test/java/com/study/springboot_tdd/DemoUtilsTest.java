package com.study.springboot_tdd;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DemoUtilsTest {

	DemoUtils demoUtils;

	@BeforeEach
	void setupBeforeEach() {
		demoUtils = new DemoUtils();
	}

	@Test
	@DisplayName("Equals and Not Equals")
	void testEqualsAndNotEquals() {

		assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
		assertNotEquals(8, demoUtils.add(1, 9), "1+9 must not be 8");
	}

	@Test
	@DisplayName("Null and Not Null")
	void testNullAndNotNull() {

		String str1 = null;
		String str2 = "duoh";

		assertNull(demoUtils.checkNull(str1), "Object should be null");
		assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
	}

	// @AfterEach
	// void tearDownAfterEach() {
	// 	System.out.println("Running @AfterEach");
	// 	System.out.println();
	// }
	//
	// @BeforeAll
	// static void setupBeforeEachClass() {
	// 	System.out.println("@BeforeAll executes only once before all test methods execution in the class");
	// }
	//
	// @AfterAll
	// static void tearDownAfterAll() {
	// 	System.out.println("@AfterAll executes only once after all test methods execution in the class");
	// }
}
