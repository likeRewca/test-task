package com.test.services.impl;

import com.test.constant.CommandConstant;
import com.test.entities.Department;
import com.test.entities.Lector;
import com.test.repositories.DepartmentRepository;
import com.test.repositories.LectorRepository;
import com.test.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final LectorRepository lectorRepository;

    @Override
    public String getDepartmentHead(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if (department == null) {
            return "Not found Department " + departmentName;
        }

        Lector lector = department.getDepartmentHead();

        return lector.getFirstName() + " " + lector.getLastName();
    }

    @Override
    @Transactional
    public String getDepartmentStat(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if (department == null) {
            return "Not found Department " + departmentName;
        }

        Set<Lector> lectors = department.getLectors();

        StringBuilder answer = new StringBuilder();

        answer.append("Assistans - ")
                .append(lectors.stream().filter(l -> l.getDegree().equals("assistant")).count()).append("\n");
        answer.append("Associate professors - ")
                .append(lectors.stream().filter(l -> l.getDegree().equals("associate professor")).count()).append("\n");
        answer.append("Professors - ")
                .append(lectors.stream().filter(l -> l.getDegree().equals("professor")).count());

        return answer.toString();
    }

    @Override
    @Transactional
    public Double averageSalary(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);

        if (department == null) {
            System.out.println("Not found Department " + departmentName);
            return Double.NaN;
        }

        return department.getLectors().stream().mapToInt(Lector::getSalary).average().orElse(Double.NaN);
    }

    @Override
    @Transactional
    public Integer countOfEmployee(String departmentName) {
        //Show count of employee for Physics
        Department department = departmentRepository.findByDepartmentName(departmentName);

        if (department == null) {
            System.out.println("Not found Department " + departmentName);
            return 0;
        }

        return department.getLectors().size();
    }

    @Override
    public String searchEmployee(String template) {
        //Global search by nov
        List<Lector> lectors = lectorRepository.findAllByFirstNameAndLastNameLike(template);

        if (!lectors.isEmpty()) {
            String answer = lectors.stream()
                    .map(lector -> lector.getFirstName() + " " + lector.getLastName())
                    .collect(Collectors.toList())
                    .toString();

            return answer.substring(1, answer.length() - 1);
        } else {
            return "Not found";
        }
    }

    @Override
    public String help() {
        String departmentName = " <department_name>";
        return new StringBuilder()
                .append("###Only these commands are available at the moment###").append("\n\n")
                .append(CommandConstant.DEPARTMENT_HEADER).append(departmentName).append("\n")
                .append(String.format("Show %s statistics", "<department_name>")).append("\n")
                .append(CommandConstant.AVG_SALARY).append(departmentName).append("\n")
                .append(CommandConstant.EMPLOYEE_COUNT).append(departmentName).append("\n")
                .append(CommandConstant.GLOBAL_SEARCH).append(departmentName)
                .toString();
    }
}
