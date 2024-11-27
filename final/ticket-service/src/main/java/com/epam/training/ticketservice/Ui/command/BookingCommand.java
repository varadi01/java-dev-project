package com.epam.training.ticketservice.Ui.command;

import com.epam.training.ticketservice.Entity.Booking;
import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Entity.Room;
import com.epam.training.ticketservice.Service.*;
import com.epam.training.ticketservice.Utils.Exceptions.BookingException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDateTime;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookingCommand {

    private final BookingService bookingService;
    private final ViewerService viewerService;
    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final RoomService roomService;
    private final PriceService priceService;

    @ShellMethod(key = "book")
    @ShellMethodAvailability("isLoggedIn")
    protected String makeBooking(String movieTitle, String roomName, String startTime, String seatsToBeBooked) {
        startTime = startTime.replace(" ", "T");

        var screening = screeningService.getScreeningByParameters(getMovieByTitle(
                        movieTitle),
                getRoomByName(roomName),
                LocalDateTime.parse(startTime)
        );

        var bookedSeats = List.of(seatsToBeBooked.split(" ")); //TODO TEST

        var price = priceService.getTicketPrice(movieTitle, roomName, LocalDateTime.parse(startTime)) * bookedSeats.size();

        try {
            bookingService.saveBooking(new Booking(
                    viewerService.getSignedInViewer(),
                    screening,
                    bookedSeats,
                    price
            ));
            final StringBuilder sb = new StringBuilder();
            sb.append("Seats booked: ");
            for (int i = 0; i < bookedSeats.size(); i++) {
                sb.append(bookedSeats.get(i));
                if (i != bookedSeats.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("; the price for this booking is ")
                    .append(price)
                    .append(price).append(" HUF");

            return sb.toString();
        } catch (BookingException e) {
            return e.getMessage();
        }
    }

    private Availability isLoggedIn() {
        return viewerService.isSignedIn() ? Availability.available()
                :
                Availability.unavailable("Not logged in");
    }

    private Movie getMovieByTitle(String movieTitle) {
        return movieService.getMovieByTitle(movieTitle);
    }

    private Room getRoomByName(String roomName) {
        return roomService.getRoomByName(roomName);
    }
}
