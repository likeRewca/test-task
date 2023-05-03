package com.test.services.commandstat;

import com.test.model.entities.Lector;
import com.test.repositories.LectorRepository;
import com.test.services.CommandStat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GlobalSearchService implements CommandStat {


    private final LectorRepository lectorRepository;

    @Override
    public String execute(String template) {
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
}
