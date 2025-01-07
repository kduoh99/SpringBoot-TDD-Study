package com.study.spring_boot_mvc_testing_demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.spring_boot_mvc_testing_demo.models.MathGrade;

@Repository
public interface MathGradeDao extends CrudRepository<MathGrade, Integer> {

	Iterable<MathGrade> findGradeByStudentId(int id);

	void deleteByStudentId(int id);
}
