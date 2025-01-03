package com.study.springboot_tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

class ConditionalTest {

	@Test
	@Disabled("Don't run until JIRA #123 is resolved")
	void basicTest() {
		// execute method and perform asserts
	}

	@Test
	@EnabledOnOs(OS.WINDOWS)
	void testForWindowsOnly() {
		// execute method and perform asserts
	}

	@Test
	@EnabledOnOs(OS.MAC)
	void testForMacOnly() {
		// execute method and perform asserts
	}

	@Test
	@EnabledOnOs({OS.MAC, OS.WINDOWS})
	void testForMacAndWindowsOnly() {
		// execute method and perform asserts
	}

	@Test
	@EnabledOnOs(OS.LINUX)
	void testForLinuxOnly() {
		// execute method and perform asserts
	}

	@Test
	@EnabledOnJre(JRE.JAVA_17)
	void testOnlyForJava17() {
		// execute method and perform asserts
	}

	@Test
	@EnabledOnJre(JRE.JAVA_21)
	void testOnlyForJava21() {
		// execute method and perform asserts
	}

	@Test
	@EnabledForJreRange(min=JRE.JAVA_13, max=JRE.JAVA_21)
	void testOnlyForJavaRange() {
		// execute method and perform asserts
	}

	@Test
	@EnabledForJreRange(min=JRE.JAVA_11)
	void testOnlyForJavaRangeMin() {
		// execute method and perform asserts
	}
}
