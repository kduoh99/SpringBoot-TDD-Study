package com.study.spring_boot_unit_testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.study.spring_boot_unit_testing.dao.ApplicationDao;
import com.study.spring_boot_unit_testing.models.CollegeStudent;
import com.study.spring_boot_unit_testing.service.ApplicationService;

@SpringBootApplication
public class SpringBootUnitTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUnitTestingApplication.class, args);
	}

	@Bean(name = "applicationExample")
	ApplicationService getApplicationService() {
		return new ApplicationService();
	}

	@Bean(name = "applicationDao")
	ApplicationDao getApplicationDao() {
		return new ApplicationDao();
	}

	@Bean(name = "collegeStudent")
	@Scope(value = "prototype")
	CollegeStudent getCollegeStudent() {
		return new CollegeStudent();
	}
}
