package com.study.springboot_tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
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
}
