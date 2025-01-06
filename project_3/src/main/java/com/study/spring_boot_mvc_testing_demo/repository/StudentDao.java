package com.study.spring_boot_mvc_testing_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.spring_boot_mvc_testing_demo.models.CollegeStudent;

@Repository
public interface StudentDao extends JpaRepository<CollegeStudent, Integer> {

	CollegeStudent findByEmailAddress(String emailAddress);
}
