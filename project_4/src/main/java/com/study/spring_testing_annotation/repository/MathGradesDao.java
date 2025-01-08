package com.study.spring_testing_annotation.repository;

import com.study.spring_testing_annotation.models.MathGrade;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathGradesDao extends CrudRepository<MathGrade, Integer> {

	Iterable<MathGrade> findGradeByStudentId(int id);

	void deleteByStudentId(int id);
}
