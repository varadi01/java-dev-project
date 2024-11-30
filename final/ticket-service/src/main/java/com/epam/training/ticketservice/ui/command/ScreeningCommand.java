package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.ViewerService;
import com.epam.training.ticketservice.utils.DateConverter;
import com.epam.training.ticketservice.utils.exceptions.ScreeningException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDateTime;

@ShellComponent
@RequiredArgsConstructor
public class ScreeningCommand {

    private final ScreeningService screeningService;
    private final ViewerService viewerService;
    private final MovieService movieService;
    private final RoomService roomService;

    @ShellMethod(key = "create screening")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String createScreening(String movieTitle, String roomName, String startTime) {
        try {
            screeningService.saveScreening(new Screening(
                    getMovieByTitle(movieTitle),
                    getRoomByName(roomName),
                    DateConverter.convertToLocalDateTime(startTime)
            ));
        } catch (ScreeningException e) {
            return e.getMessage();
        }
        return "Screening created";
    }

    @ShellMethod(key = "delete screening")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String deleteScreening(String movieTitle, String roomName, String startTime) {
        var screening = screeningService.getScreeningByParameters(
                getMovieByTitle(movieTitle),
                getRoomByName(roomName),
                DateConverter.convertToLocalDateTime(startTime)
        );

        if (screening == null) {
            return "Screening not found";
        }

        screeningService.deleteScreening(screening.getId());
        return "Screening deleted";
    }

    @ShellMethod(key = "list screenings")
    protected String listScreenings() {
        var screenings = screeningService.getAllScreenings();
        if (screenings.isEmpty()) {
            return "There are no screenings";
        }
        var sb = new StringBuilder();
        for (Screening screening : screenings) {
            sb.append(screening).append("\n");
        }
        return sb.toString();
    }

    private Availability isLoggedInAsPrivileged() {
        return viewerService.isSignedInAsPrivileged() ? Availability.available()
                :
                Availability.unavailable("Not admin or not logged in");
    }

    private Movie getMovieByTitle(String movieTitle) {
        return movieService.getMovieByTitle(movieTitle);
    }

    private Room getRoomByName(String roomName) {
        return roomService.getRoomByName(roomName);
    }
}
