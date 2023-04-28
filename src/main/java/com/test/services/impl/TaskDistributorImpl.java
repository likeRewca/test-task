package com.test.services.impl;

import com.test.constant.CommandConstant;
import com.test.services.DepartmentService;
import com.test.services.TaskDistributor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@RequiredArgsConstructor
public class TaskDistributorImpl implements TaskDistributor {

    private final DepartmentService service;

    @Override
    public void run() throws IOException {
        System.out.println("### Hello University! ###");
        System.out.println("### Please use command - <help> find the right command! ###");
        System.out.println("### To finish work write - <exit>! Good luck! ###");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String line = br.readLine();

            if (line.isEmpty()) {
                continue;
            }

            commandFilter(line);

            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Bye bye!");
                break;
            }
        }
    }

    private void commandFilter(String reqLine) {
        String departmentHead;

        if (reqLine.startsWith(CommandConstant.DEPARTMENT_HEADER)) {
            System.out.println(service
                    .getDepartmentHead(cutCommand(CommandConstant.DEPARTMENT_HEADER, reqLine)));
        } else if (reqLine.startsWith(CommandConstant.AVG_SALARY)) {
            System.out.println(service
                    .averageSalary(cutCommand(CommandConstant.AVG_SALARY, reqLine)));
        } else if (reqLine.startsWith(CommandConstant.EMPLOYEE_COUNT)) {
            System.out.println(service
                    .countOfEmployee(cutCommand(CommandConstant.EMPLOYEE_COUNT, reqLine)));
        } else if (reqLine.startsWith(CommandConstant.GLOBAL_SEARCH)) {
            System.out.println(service
                    .searchEmployee(cutCommand(CommandConstant.GLOBAL_SEARCH, reqLine)));
        } else if (reqLine.matches(CommandConstant.DEPARTMENT_STAT)) {
            departmentHead = reqLine
                    .replaceAll("Show ", "")
                    .replaceAll(" statistics", "");

            System.out.println(service.getDepartmentStat(departmentHead));
        } else if (reqLine.equalsIgnoreCase("help")) {
            System.out.println(service.help());
        } else if (!reqLine.equalsIgnoreCase("exit")) {
            System.out.println("Sorry, I don't know this command!");
        }
    }

    private String cutCommand(String command, String reqLine) {
        return reqLine.replaceAll(command, "").trim();
    }
}
