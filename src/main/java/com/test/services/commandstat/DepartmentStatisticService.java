package com.test.services.commandstat;

import com.test.model.entities.Department;
import com.test.model.entities.Lector;
import com.test.repositories.DepartmentRepository;
import com.test.services.CommandStat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentStatisticService implements CommandStat {

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public String execute(String template) {
        Department department = departmentRepository.findByDepartmentName(template);
        if (department == null) {
            return "Not found Department " + template;
        }

        Set<Lector> lectors = department.getLectors();

        StringBuilder answer = new StringBuilder();

        answer.append("Assistants - ")
                .append(lectors.stream().filter(l -> l.getDegree().equals("assistant")).count()).append("\n");
        answer.append("Associate professors - ")
                .append(lectors.stream().filter(l -> l.getDegree().equals("associate professor")).count()).append("\n");
        answer.append("Professors - ")
                .append(lectors.stream().filter(l -> l.getDegree().equals("professor")).count());

        return answer.toString();
    }
}
