package com.test.services.commandstat;

import com.test.model.Command;
import com.test.services.CommandStat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.test.model.Command.*;

@Component
@RequiredArgsConstructor
public class CommandStatKeeper {

    private Map<Command, CommandStat> mapStat = new HashMap<>();

    private final AverageSalaryService averageSalaryService;

    private final CountEmployeeService countEmployeeService;

    private final DepartmentHeadService departmentHeadService;

    private final DepartmentStatisticService statisticService;

    private final GlobalSearchService searchService;

    private final HelpService helpService;

    private final ExitService exitService;


    @PostConstruct
    private void init() {
        mapStat.put(AVG_SALARY, averageSalaryService);
        mapStat.put(EMPLOYEE_COUNT, countEmployeeService);
        mapStat.put(DEPARTMENT_HEADER, departmentHeadService);
        mapStat.put(DEPARTMENT_STAT, statisticService);
        mapStat.put(SEARCH, searchService);
        mapStat.put(HELP, helpService);
        mapStat.put(EXIT, exitService);
    }

    public CommandStat get(Command command) {
        return mapStat.get(command);
    }

}
