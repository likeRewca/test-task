package com.test.util;

import com.test.model.Command;

public class CutCommandUtil {

    private CutCommandUtil() {}

    public static String cutCommand(Command command, String reqLine) {
        if(command.equals(Command.DEPARTMENT_STAT)) {
            return reqLine
                    .replaceAll("Show ", "")
                    .replaceAll(" statistics", "");
        }

        return reqLine.replaceAll(command.cut(), "");
    }
}
