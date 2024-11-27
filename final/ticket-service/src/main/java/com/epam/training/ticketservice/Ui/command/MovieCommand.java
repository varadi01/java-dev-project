package com.epam.training.ticketservice.Ui.command;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Entity.Screening;
import com.epam.training.ticketservice.Service.MovieService;
import com.epam.training.ticketservice.Service.ViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class MovieCommand {
    private final MovieService movieService;
    private final ViewerService viewerService;

    @ShellMethod(key = "create movie")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String createMovie(String title, String genre, int lengthInMinutes) {
        movieService.saveMovie(new Movie(title, genre, lengthInMinutes));
        return "Movie created";
    }

    @ShellMethod(key = "update movie")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String updateMovie(String title, String genre, int lengthInMinutes) {
        movieService.updateMovie(new Movie(title, genre, lengthInMinutes));
        return "Movie updated";
    }

    @ShellMethod(key = "delete movie")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String deleteMovie(String movieTitle) {
        movieService.deleteMovie(movieTitle);
        return "Movie deleted";
    }

    @ShellMethod(key = "list movies")
    protected String listMovies() {
        var movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }
        var sb = new StringBuilder();
        for (Movie movie : movies) {
            sb.append(movie).append("\n");
        }
        return sb.toString();
    }

    private Availability isLoggedInAsPrivileged(){
        return viewerService.isSignedInAsPrivileged() ? Availability.available()
                :
                Availability.unavailable("Not admin or not logged in");
    }
}
