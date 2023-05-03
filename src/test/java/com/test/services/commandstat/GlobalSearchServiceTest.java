package com.test.services.commandstat;

import com.test.model.entities.Lector;
import com.test.repositories.LectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalSearchServiceTest {

    @InjectMocks
    private GlobalSearchService globalSearchService;

    @Mock
    private LectorRepository lectorRepository;

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

        assertEquals(expectedResult, globalSearchService.execute(template));
    }

    @Test
    void testSearchEmployeeNotFoundMatch() {
        String template = "ddd";

        when(lectorRepository.findAllByFirstNameAndLastNameLike(template))
                .thenReturn(List.of());

        String expectedResult = "Not found";

        assertEquals(expectedResult, globalSearchService.execute(template));
    }
}