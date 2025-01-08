package com.study.spring_testing_annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.study.spring_testing_annotation.models.CollegeStudent;
import com.study.spring_testing_annotation.models.Grade;
import com.study.spring_testing_annotation.models.HistoryGrade;
import com.study.spring_testing_annotation.models.MathGrade;
import com.study.spring_testing_annotation.models.ScienceGrade;

@SpringBootApplication
public class SpringTestingAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestingAnnotationApplication.class, args);
	}

	@Bean
	@Scope(value = "prototype")
	CollegeStudent getCollegeStudent() {
		return new CollegeStudent();
	}

	@Bean
	@Scope(value = "prototype")
	Grade getMathGrade(double grade) {
		return new MathGrade(grade);
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("mathGrades")
	MathGrade getGrade() {
		return new MathGrade();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("scienceGrades")
	ScienceGrade getScienceGrade() {
		return new ScienceGrade();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("historyGrades")
	HistoryGrade getHistoryGrade() {
		return new HistoryGrade();
	}
}
