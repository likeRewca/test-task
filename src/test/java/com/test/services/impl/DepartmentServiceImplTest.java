package com.test.services.impl;

import com.test.entities.Department;
import com.test.entities.Lector;
import com.test.repositories.DepartmentRepository;
import com.test.repositories.LectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private LectorRepository lectorRepository;

    private String reqDepartment = "Physics";

    @Test
    void testGetDepartmentHead() {
        Lector lector = Lector.builder()
                .id(1)
                .firstName("Tom")
                .lastName("Smith")
                .degree("professor")
                .salary(400)
                .build();

        Department department = Department.builder()
                .id(1)
                .departmentName(reqDepartment)
                .departmentHead(lector)
                .build();

        when(departmentRepository.findByDepartmentName(reqDepartment)).thenReturn(department);

        String expectedResult = lector.getFirstName() + " " + lector.getLastName();

        assertEquals(expectedResult, departmentService.getDepartmentHead(reqDepartment));

    }

    @Test
    void testGetDepartmentHeadNotFoundDepartment() {
        String nonExistentDepartment = "Cancel";

        when(departmentRepository.findByDepartmentName(nonExistentDepartment)).thenReturn(null);

        String expectedResult = "Not found Department " + nonExistentDepartment;

        assertEquals(expectedResult, departmentService.getDepartmentHead(nonExistentDepartment));
    }

    @Test
    void testGetDepartmentStat() {
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
                .append("Assistans - 1\n")
                .append("Associate professors - 0\n")
                .append("Professors - 1").toString();

        assertEquals(expectedAnswer, departmentService.getDepartmentStat(reqDepartment));
    }

    @Test
    void testGetDepartmentStatNotFoundDepartment() {
        String nonExistentDepartment = "Cancel";

        when(departmentRepository.findByDepartmentName(nonExistentDepartment)).thenReturn(null);

        String expectedResult = "Not found Department " + nonExistentDepartment;

        assertEquals(expectedResult, departmentService.getDepartmentStat(nonExistentDepartment));
    }

    @Test
    void testGetDepartmentStatWithoutLectors() {
        Department department = Department.builder()
                .id(1)
                .departmentName(reqDepartment)
                .lectors(Set.of())
                .build();

        when(departmentRepository.findByDepartmentName(reqDepartment)).thenReturn(department);

        String expectedAnswer = new StringBuilder()
                .append("Assistans - 0\n")
                .append("Associate professors - 0\n")
                .append("Professors - 0").toString();

        assertEquals(expectedAnswer, departmentService.getDepartmentStat(reqDepartment));
    }

    @Test
    void testAverageSalary() {
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

        Double expectedResult = 250.0;

        assertEquals(expectedResult, departmentService.averageSalary(reqDepartment));
    }

    @Test
    void testAverageSalaryFoundDepartment() {
        String nonExistentDepartment = "Cancel";

        when(departmentRepository.findByDepartmentName(nonExistentDepartment)).thenReturn(null);

        Double expectedResult = Double.NaN;

        assertEquals(expectedResult, departmentService.averageSalary(nonExistentDepartment));
    }

    @Test
    void testCountOfEmployee() {
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

        Integer expectedResult = 2;

        assertEquals(expectedResult, departmentService.countOfEmployee(reqDepartment));
    }

    @Test
    void testCountOfEmployeeFoundDepartment() {
        String nonExistentDepartment = "Cancel";

        when(departmentRepository.findByDepartmentName(nonExistentDepartment)).thenReturn(null);

        Integer expectedResult = 0;

        assertEquals(expectedResult, departmentService.countOfEmployee(nonExistentDepartment));
    }

    @Test
    void testSearchEmployee() {
        String template = "om";

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

        when(lectorRepository.findAllByFirstNameAndLastNameLike(template))
                .thenReturn(List.of(lectorProf, lectorAssistant));

        String expectedResult = String.format("%s %s, %s %s",
                lectorProf.getFirstName(),
                lectorProf.getLastName(),
                lectorAssistant.getFirstName(),
                lectorAssistant.getLastName());

        assertEquals(expectedResult, departmentService.searchEmployee(template));
    }

    @Test
    void testSearchEmployeeNotFoundMatch() {
        String template = "ddd";

        when(lectorRepository.findAllByFirstNameAndLastNameLike(template))
                .thenReturn(List.of());

        String expectedResult = "Not found";

        assertEquals(expectedResult, departmentService.searchEmployee(template));
    }
}