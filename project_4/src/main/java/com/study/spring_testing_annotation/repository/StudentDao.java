package com.study.spring_testing_annotation.repository;

import com.study.spring_testing_annotation.models.CollegeStudent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<CollegeStudent, Integer> {

	CollegeStudent findByEmailAddress(String emailAddress);
}
