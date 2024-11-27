package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.PriceComponent;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDateTime;

@ShellComponent
@RequiredArgsConstructor
public class PriceCommand {

    private final PriceService priceService;
    private final ViewerService viewerService;
    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final RoomService roomService;

    @ShellMethod(key = "update base price")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String updateBasePrice(int basePrice) {
        priceService.updateBasePrice(basePrice);
        return "Base price updated";
    }

    @ShellMethod(key = "create price component")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String createPriceComponent(String componentName, int componentValue) {
        priceService.savePriceComponent(new PriceComponent(componentName, componentValue));
        return "Price component created";
    }

    @ShellMethod(key = "attach price component to room")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String attachPriceComponentToRoom(String componentName, String roomName) {
        priceService.attachPriceComponent(
                priceService.getPriceComponentByName(componentName),
                roomService.getRoomByName(roomName)
        );
        return "Price component attached to room";
    }

    @ShellMethod(key = "attach price component to movie")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String attachPriceComponentToMovie(String componentName, String movieTitle) {
        priceService.attachPriceComponent(
                priceService.getPriceComponentByName(componentName),
                getMovieByTitle(movieTitle)
        );
        return "Price component attached to movie";
    }

    @ShellMethod(key = "attach price component to screening")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String attachPriceComponentToScreening(String componentName, String movieTitle, String roomName, String startTime) {
        startTime = startTime.replace(" ", "T");

        var screening = screeningService.getScreeningByParameters(getMovieByTitle(
                        movieTitle),
                getRoomByName(roomName),
                LocalDateTime.parse(startTime)
        );

        priceService.attachPriceComponent(
                priceService.getPriceComponentByName(componentName),
                screening
        );
        return "Price component attached to screening";
    }

    @ShellMethod(key = "show price for")
    protected String showPrice(String movieTitle, String roomName, String startTime, String seats) {
        startTime = startTime.replace(" ", "T");

        var numberOfTickets = seats.split(" ").length;
        var pricePerTicket = priceService.getTicketPrice(movieTitle, roomName, LocalDateTime.parse(startTime));
        var price = pricePerTicket * numberOfTickets;

        return "The price for this booking would be " + price + " HUF";
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
