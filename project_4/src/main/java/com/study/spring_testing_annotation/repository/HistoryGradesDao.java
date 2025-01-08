package com.study.spring_testing_annotation.repository;

import com.study.spring_testing_annotation.models.HistoryGrade;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryGradesDao extends CrudRepository<HistoryGrade, Integer> {

	Iterable<HistoryGrade> findGradeByStudentId(int id);

	void deleteByStudentId(int id);
}
