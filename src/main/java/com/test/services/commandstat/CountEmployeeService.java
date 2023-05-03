package com.test.services.commandstat;

import com.test.model.entities.Department;
import com.test.repositories.DepartmentRepository;
import com.test.services.CommandStat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountEmployeeService implements CommandStat {

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public String execute(String template) {
        Department department = departmentRepository.findByDepartmentName(template);

        if (department == null) {
            return "Not found Department " + template;
        }

        return String.valueOf(department.getLectors().size());
    }
}
