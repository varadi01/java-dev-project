package com.epam.training.ticketservice.Ui.command;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Entity.Room;
import com.epam.training.ticketservice.Entity.Screening;
import com.epam.training.ticketservice.Service.MovieService;
import com.epam.training.ticketservice.Service.RoomService;
import com.epam.training.ticketservice.Service.ScreeningService;
import com.epam.training.ticketservice.Service.ViewerService;
import com.epam.training.ticketservice.Utils.Exceptions.ScreeningException;
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
        startTime = startTime.replace(" ", "T");
        System.out.println(startTime);
        try {
            screeningService.saveScreening(new Screening(
                    getMovieByTitle(movieTitle),
                    getRoomByName(roomName),
                    LocalDateTime.parse(startTime) //TODO test
            ));
        } catch (ScreeningException e) {
            return e.getMessage();
        }
        return "Screening created";
    }

    @ShellMethod(key = "delete screening")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String deleteScreening(String movieTitle, String roomName, String startTime) {
        startTime = startTime.replace(" ", "T");

        var screening = screeningService.getScreeningByParameters(
                getMovieByTitle(movieTitle),
                getRoomByName(roomName),
                LocalDateTime.parse(startTime)
        );

        if (screening == null) return "Screening not found";

        screeningService.deleteScreening(screening.getId());
        return "Screening deleted";
    }

    @ShellMethod(key = "list screenings")
    protected String listScreenings() {
        var screenings = screeningService.getAllScreenings();
        if (screenings.isEmpty()){
            return "There are no screenings";
        }
        var sb = new StringBuilder();
        for (Screening screening : screenings) {
            sb.append(screening).append("\n");
        }
        return sb.toString();
    }

    private Availability isLoggedInAsPrivileged(){
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
