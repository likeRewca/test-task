package com.test.services;

import java.util.List;

public interface DepartmentService {

    String getDepartmentHead(String departmentName);

    String getDepartmentStat(String departmentName);

    Double averageSalary(String departmentName);

    Integer countOfEmployee(String departmentName);

    String searchEmployee(String template);

    String help();
}
