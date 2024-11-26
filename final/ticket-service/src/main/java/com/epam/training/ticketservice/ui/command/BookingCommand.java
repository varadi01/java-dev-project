package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class BookingCommand {

    private final BookingService bookingService;

    @ShellMethod(key = "book")
    protected String makeBooking(String movieTitle, String roomName, String startTime, String seatsToBeBooked){
        return "";
    }
}
