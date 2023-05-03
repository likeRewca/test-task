package com.test.model;

import com.test.constant.CommandConstant;

public enum Command {

    HELP(CommandConstant.HELP),
    EMPLOYEE_COUNT(CommandConstant.EMPLOYEE_COUNT),
    DEPARTMENT_HEADER(CommandConstant.DEPARTMENT_HEADER),
    AVG_SALARY(CommandConstant.AVG_SALARY),
    SEARCH(CommandConstant.GLOBAL_SEARCH),
    DEPARTMENT_STAT(CommandConstant.DEPARTMENT_STAT),
    EXIT(CommandConstant.EXIT),
    NO_STATE("Not found command");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command getCommands(String stringValue) {
        for(Command command : Command.values()) {
            if(stringValue.matches(command.getValue())) {
                return command;
            }
        }

        return NO_STATE;
    }

    public String getValue() {
        return value;
    }

    public String cut() {
        return value.replaceAll("\\(\\.\\*\\)", "");
    }
}
