package com.study.spring_testing_annotation.repository;

import com.study.spring_testing_annotation.models.ScienceGrade;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceGradesDao extends CrudRepository<ScienceGrade, Integer> {

	Iterable<ScienceGrade> findGradeByStudentId(int id);

	void deleteByStudentId(int id);
}
