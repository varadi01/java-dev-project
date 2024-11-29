package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.entity.Booking;
import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.service.BookingService;
import com.epam.training.ticketservice.service.ViewerService;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.PriceService;
import com.epam.training.ticketservice.utils.DateConverter;
import com.epam.training.ticketservice.utils.exceptions.BookingException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

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
    protected String makeBooking(String movieTitle, String roomName, String startTimeString, String seatsToBeBooked) {
        var startTime = DateConverter.convertToLocalDateTime(startTimeString);

        var screening = screeningService.getScreeningByParameters(getMovieByTitle(
                        movieTitle),
                getRoomByName(roomName),
                startTime
        );

        var bookedSeats = List.of(seatsToBeBooked.split(" ")); //TODO TEST

        var price = priceService.getTicketPrice(movieTitle, roomName, startTime) * bookedSeats.size();

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
                sb.append("(").append(bookedSeats.get(i)).append(")");
                if (i != bookedSeats.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("; the price for this booking is ")
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
