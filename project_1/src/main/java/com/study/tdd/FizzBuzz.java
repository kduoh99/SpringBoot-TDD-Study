package com.study.tdd;

public class FizzBuzz {

	// If number is divisible by 3, print Fizz
	// If number is divisible by 5, print Buzz
	// If number is divisible by 3 and 5, print FizzBuzz
	// If number is NOT divisible by 3 or 5, then print the number

	public static String compute(int i) {

		StringBuilder sb = new StringBuilder();

		if (i % 3 == 0) {
			sb.append("Fizz");
		}

		if (i % 5 == 0) {
			sb.append("Buzz");
		}

		if (sb.isEmpty()) {
			sb.append(i);
		}

		return sb.toString();
	}

	/*
	public static String compute(int i) {

		if ((i % 3 == 0) && (i % 5 == 0)) {
			return "FizzBuzz";
		} else if (i % 3 == 0) {
			return "Fizz";
		} else if (i % 5 == 0) {
			return "Buzz";
		} else {
			return Integer.toString(i);
		}
	}
	*/
}
