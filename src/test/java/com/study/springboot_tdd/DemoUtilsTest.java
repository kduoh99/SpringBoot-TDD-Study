package com.study.springboot_tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

	@Test
	void testEqualsAndNotEquals() {

		// set up
		DemoUtils demoUtils = new DemoUtils();

		// execute and assert
		assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
		assertNotEquals(8, demoUtils.add(1, 9), "1+9 must not be 8");
	}

	@Test
	void testNullAndNotNull() {

		DemoUtils demoUtils = new DemoUtils();
		String str1 = null;
		String str2 = "duoh";

		assertNull(demoUtils.checkNull(str1), "Object should be null");
		assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
	}
}
