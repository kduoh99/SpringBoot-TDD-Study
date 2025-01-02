package com.study.springboot_tdd;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoUtilsTest {

	DemoUtils demoUtils;

	@BeforeEach
	void setupBeforeEach() {
		demoUtils = new DemoUtils();
	}

	@Test
	@DisplayName("Equals and Not Equals")
	@Order(1)
	void testEqualsAndNotEquals() {

		assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
		assertNotEquals(8, demoUtils.add(1, 9), "1+9 must not be 8");
	}

	@Test
	@DisplayName("Null and Not Null")
	@Order(0)
	void testNullAndNotNull() {
		String str1 = null;
		String str2 = "duoh";

		assertNull(demoUtils.checkNull(str1), "Object should be null");
		assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
	}

	@Test
	@DisplayName("Same and Not Same")
	void testSameAndNotSame() {
		String str = "duoh";

		assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should refer to same object");
		assertNotSame(str, demoUtils.getAcademy(), "Objects should not refer same object");
	}

	@Test
	@DisplayName("True and False")
	@Order(30)
	void testTrueFalse() {
		int gradeOne = 10;
		int gradeTwo = 5;

		assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "This should return true");
		assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "This should return false");
	}

	@Test
	@DisplayName("Array Equals")
	void testArrayEquals() {
		String[] stringArray = {"A", "B", "C"};

		assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
	}

	@Test
	@DisplayName("Iterable Equals")
	void testIterableEquals() {
		List<String> theList = List.of("luv", "2", "code");

		assertIterableEquals(theList, demoUtils.getAcademyInList(), "Expected list should be same as actual list");
	}

	@Test
	@DisplayName("Lines Match")
	@Order(50)
	void testLinesMatch() {
		List<String> theList = List.of("luv", "2", "code");

		assertLinesMatch(theList, demoUtils.getAcademyInList(), "Lines should match");
	}

	@Test
	@DisplayName("Throws and Does Not Throw")
	void testThrowsAndDoesNotThrow() {

		assertThrows(Exception.class, () -> { demoUtils.throwException(-1); }, "Should throw exception");
		assertDoesNotThrow(() -> { demoUtils.throwException(5); }, "Should not throw exception");
	}

	@Test
	@DisplayName("Timeout")
	void testTimeout() {

		assertTimeoutPreemptively(Duration.ofSeconds(3), () -> { demoUtils.checkTimeout(); }, "Method should execute in 3 seconds");
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
