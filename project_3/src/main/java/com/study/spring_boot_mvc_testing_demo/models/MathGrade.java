package com.study.spring_boot_mvc_testing_demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "math_grade")
public class MathGrade implements Grade {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "student_id")
	private int studentId;
	@Column(name = "grade")
	private double grade;

	public MathGrade() {

	}

	public MathGrade(double grade) {
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
