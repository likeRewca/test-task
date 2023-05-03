package com.test.services.commandstat;

import com.test.model.entities.Department;
import com.test.model.entities.Lector;
import com.test.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentStatisticServiceTest {
    @InjectMocks
    private DepartmentStatisticService departmentStatisticService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void testGetDepartmentStat() {
        String reqDepartment = "Physics";

        Lector lectorProf = Lector.builder()
                .id(1)
                .firstName("Tom")
                .lastName("Smith")
                .degree("professor")
                .salary(400)
                .build();

        Lector lectorAssistant = Lector.builder()
                .id(1)
                .firstName("Rom")
                .lastName("Nelson")
                .degree("assistant")
                .salary(100)
                .build();

        Department department = Department.builder()
                .id(1)
                .departmentName(reqDepartment)
                .departmentHead(lectorProf)
                .lectors(Set.of(lectorProf,lectorAssistant))
                .build();

        when(departmentRepository.findByDepartmentName(reqDepartment)).thenReturn(department);

        String expectedAnswer = new StringBuilder()
                .append("Assistants - 1\n")
                .append("Associate professors - 0\n")
                .append("Professors - 1").toString();

        assertEquals(expectedAnswer, departmentStatisticService.execute(reqDepartment));
    }

    @Test
    void testGetDepartmentStatNotFoundDepartment() {
        String nonExistentDepartment = "Cancel";

        when(departmentRepository.findByDepartmentName(nonExistentDepartment)).thenReturn(null);

        String expectedResult = "Not found Department " + nonExistentDepartment;

        assertEquals(expectedResult, departmentStatisticService.execute(nonExistentDepartment));
    }
}