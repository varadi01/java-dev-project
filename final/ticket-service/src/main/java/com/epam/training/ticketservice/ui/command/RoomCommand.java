package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class RoomCommand {

    private final RoomService roomService;

    @ShellMethod(key = "create room")
    protected String createMovie(String name, int numberOfRows, int numberOfColumns) {
        return "";
    }

    @ShellMethod(key = "update room")
    protected String updateMovie(String name, int numberOfRows, int numberOfColumns) {
        return "";
    }

    @ShellMethod(key = "delete room")
    protected String deleteMovie(String roomName) {
        return "";
    }

    @ShellMethod(key = "list rooms")
    protected String listMovies() {
        return "";
    }
}
