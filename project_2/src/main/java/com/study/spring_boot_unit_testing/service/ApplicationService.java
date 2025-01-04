package com.study.spring_boot_unit_testing.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.spring_boot_unit_testing.dao.ApplicationDao;

import java.util.List;

public class ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    public double addGradeResultsForSingleClass(List<Double> numbers) {
        return applicationDao.addGradeResultsForSingleClass(numbers);
    }

    public double findGradePointAverage (List<Double> grades ) {
        return applicationDao.findGradePointAverage(grades);
    }

    public Object checkNull(Object obj) {
        return applicationDao.checkNull(obj);
    }

}
