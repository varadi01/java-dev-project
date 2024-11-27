package com.epam.training.ticketservice.Ui.command;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ProcessWideCommand {

    @ShellMethod(key = "exit")
    public void exit() {
        System.exit(0);
    }
}
