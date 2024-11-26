package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.Entity.Screening;
import com.epam.training.ticketservice.Service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ScreeningCommand {

    private final ScreeningService screeningService;

    @ShellMethod(key = "create screening")
    protected String createScreening(String movieTitle, String roomName, String startTime) {
        return "";
    }

    @ShellMethod(key = "delete screening")
    protected String deleteScreening(String movieTitle, String roomName, String startTime) {
        return "";
    }

    @ShellMethod(key = "list screenings")
    protected String listScreenings() {
        return "";
    }
}
