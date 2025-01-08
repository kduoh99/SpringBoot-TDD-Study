package com.study.spring_boot_mvc_testing_demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class CollegeStudent implements Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 테스트에서 ID를 수동으로 설정 (주석 처리 후, 테스트 진행)
	private int id;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column(name = "email_address")
	private String emailAddress;

	public CollegeStudent() {

	}

	public CollegeStudent(String firstname, String lastname, String emailAddress) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailAddress = emailAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return getFirstname() + " " + getLastname();
	}

	@Override
	public String toString() {
		return "CollegeStudent{" +
			"id=" + id +
			", firstname='" + firstname + '\'' +
			", lastname='" + lastname + '\'' +
			", emailAddress='" + emailAddress + '\'' +
			'}';
	}

	public String studentInformation() {
		return getFullName() + " " + getEmailAddress();
	}
}
