package com.test.services.commandstat;

import com.test.services.CommandStat;
import org.springframework.stereotype.Service;

@Service
public class ExitService implements CommandStat {

    @Override
    public String execute(String template) {
        return "Bye Bye!";
    }
}
