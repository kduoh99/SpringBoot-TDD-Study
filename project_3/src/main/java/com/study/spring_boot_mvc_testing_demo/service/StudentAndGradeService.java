package com.study.spring_boot_mvc_testing_demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.spring_boot_mvc_testing_demo.models.CollegeStudent;
import com.study.spring_boot_mvc_testing_demo.repository.StudentDao;

@Service
@Transactional
public class StudentAndGradeService {

	@Autowired
	private StudentDao studentDao;

	public void createStudent(String firstName, String lastName, String emailAddress) {
		CollegeStudent student = new CollegeStudent(firstName, lastName, emailAddress);
		student.setId(0);
		studentDao.save(student);
	}

	public boolean checkIfStudentIsNull(int id) {
		Optional<CollegeStudent> student = studentDao.findById(id);
		if (student.isPresent()) {
			return true;
		}
		return false;
	}

	public void deleteStudent(int id) {
		if (checkIfStudentIsNull(id)) {
			studentDao.deleteById(id);
		}
	}

	public Iterable<CollegeStudent> getGradebook() {
		Iterable<CollegeStudent> collegeStudents = studentDao.findAll();
		return collegeStudents;
	}
}
