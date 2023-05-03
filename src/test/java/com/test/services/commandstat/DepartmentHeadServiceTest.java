package com.test.services.commandstat;

import com.test.model.entities.Department;
import com.test.model.entities.Lector;
import com.test.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentHeadServiceTest {
    @InjectMocks
    private DepartmentHeadService departmentHeadService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void testGetDepartmentHead() {
        String reqDepartment = "Physics";

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

        assertEquals(expectedResult, departmentHeadService.execute(reqDepartment));

    }

    @Test
    void testGetDepartmentHeadNotFoundDepartment() {
        String nonExistentDepartment = "Cancel";

        when(departmentRepository.findByDepartmentName(nonExistentDepartment)).thenReturn(null);

        String expectedResult = "Not found Department " + nonExistentDepartment;

        assertEquals(expectedResult, departmentHeadService.execute(nonExistentDepartment));
    }
}