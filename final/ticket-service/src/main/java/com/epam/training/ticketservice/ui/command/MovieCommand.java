package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class MovieCommand {
    private final MovieService movieService;

    @ShellMethod(key = "create movie")
    protected String createMovie(String title, String genre, int lengthInMinutes) {
        return "";
    }

    @ShellMethod(key = "update movie")
    protected String updateMovie(String title, String genre, int lengthInMinutes) {
        return "";
    }

    @ShellMethod(key = "delete movie")
    protected String deleteMovie(String movieTitle) {
        return "";
    }

    @ShellMethod(key = "list movies")
    protected String listMovies() {
        return "";
    }
}
