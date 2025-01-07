package com.study.spring_boot_mvc_testing_demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.spring_boot_mvc_testing_demo.models.HistoryGrade;

@Repository
public interface HistoryGradeDao extends CrudRepository<HistoryGrade, Integer> {

	Iterable<HistoryGrade> findGradeByStudentId(int id);

	void deleteByStudentId(int id);
}
