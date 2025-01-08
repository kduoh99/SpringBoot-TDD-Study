package com.study.spring_testing_annotation.models;

import org.springframework.stereotype.Component;

@Component
public interface Student {

	String studentInformation();

	String getFullName();

}
