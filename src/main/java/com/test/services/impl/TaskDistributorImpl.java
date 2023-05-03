package com.test.services.impl;

import com.test.model.Command;
import com.test.services.CommandStat;
import com.test.services.TaskDistributor;
import com.test.services.commandstat.CommandStatKeeper;
import com.test.util.CutCommandUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@RequiredArgsConstructor
public class TaskDistributorImpl implements TaskDistributor {

    private final CommandStatKeeper commandStatKeeper;

    @Override
    public void run(Boolean runProject) throws IOException {
        System.out.println("### Hello University! ###");
        System.out.println("### Please use command - <help> find the right command! ###");
        System.out.println("### To finish work write - <exit>! Good luck! ###");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (runProject) {
            String line = br.readLine();

            if (line.isEmpty()) {
                continue;
            }

            Command currentCommend = Command.getCommands(line);

            CommandStat commandStat = commandStatKeeper.get(currentCommend);

            String temp = CutCommandUtil.cutCommand(currentCommend, line);

            if (currentCommend.equals(Command.EXIT)) {
                runProject = false;
            }

            System.out.println(commandStat.execute(temp));
        }
    }
}
