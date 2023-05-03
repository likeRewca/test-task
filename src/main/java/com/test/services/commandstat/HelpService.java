package com.test.services.commandstat;

import com.test.constant.CommandConstant;
import com.test.services.CommandStat;
import org.springframework.stereotype.Service;


@Service
public class HelpService implements CommandStat {
    @Override
    public String execute(String template) {
        String departmentName = " <department_name>";
        String temp = " <template>";

        return new StringBuilder()
                .append("###Only these commands are available at the moment###").append("\n\n")
                .append(CommandConstant.DEPARTMENT_HEADER).append(departmentName).append("\n")
                .append(String.format("Show %s statistics", "<department_name>")).append("\n")
                .append(CommandConstant.AVG_SALARY).append(departmentName).append("\n")
                .append(CommandConstant.EMPLOYEE_COUNT).append(departmentName).append("\n")
                .append(CommandConstant.GLOBAL_SEARCH).append(temp)
                .toString();
    }
}
