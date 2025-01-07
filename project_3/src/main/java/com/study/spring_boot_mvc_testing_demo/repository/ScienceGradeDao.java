package com.study.spring_boot_mvc_testing_demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.spring_boot_mvc_testing_demo.models.ScienceGrade;

@Repository
public interface ScienceGradeDao extends CrudRepository<ScienceGrade, Integer> {

	Iterable<ScienceGrade> findGradeByStudentId(int id);

	void deleteByStudentId(int id);
}
