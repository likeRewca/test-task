package com.test.services.commandstat;

import com.test.model.entities.Department;
import com.test.model.entities.Lector;
import com.test.repositories.DepartmentRepository;
import com.test.services.CommandStat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentHeadService implements CommandStat {

    private final DepartmentRepository departmentRepository;

    @Override
    public String execute(String template) {
        Department department = departmentRepository.findByDepartmentName(template);
        if (department == null) {
            return "Not found Department " + template;
        }

        Lector lector = department.getDepartmentHead();

        return lector.getFirstName() + " " + lector.getLastName();
    }
}
