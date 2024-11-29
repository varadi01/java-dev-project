package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Booking;
import com.epam.training.ticketservice.repository.BookingRepository;
import com.epam.training.ticketservice.utils.exceptions.BookingException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final PriceService priceService;

    public BookingService(BookingRepository bookingRepository, PriceService priceService) {
        this.bookingRepository = bookingRepository;
        this.priceService = priceService;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByViewer(String viewerName) {
        return bookingRepository.findAllByViewerUsername(viewerName);
    }

    public void saveBooking(Booking booking) throws BookingException {
        var existingBookings = bookingRepository.findAllByScreening(booking.getScreening());
        var bookedSeats = new ArrayList<String>();

        for (var existingBooking : existingBookings) {
            bookedSeats.addAll(existingBooking.getBookedSeats());
        }

        var seatsToBeBooked = booking.getBookedSeats();

        for (var seat : bookedSeats) {
            for (var seatToBeBooked : seatsToBeBooked) {
                if (seat.equals(seatToBeBooked)) {
                    throw new BookingException("Seat (" + seat + ") is already taken");
                }
            }
        }

        var numberOfRows = booking.getScreening().getRoom().getNumberOfSeatRows();
        var numberOfColumns = booking.getScreening().getRoom().getNumberOfSeatColumns();

        for (var seat : seatsToBeBooked) {
            var indices = seat.split(",");

            if (Integer.parseInt(indices[0]) < 0 || Integer.parseInt(indices[1]) < 0) {
                throw new BookingException("Seat " + seat + "does not exist in this room");
            }

            if (Integer.parseInt(indices[0]) > numberOfRows || Integer.parseInt(indices[1]) > numberOfColumns) {
                throw new BookingException("Seat " + seat + "does not exist in this room");
            }
        }

        booking.setPrice(priceService.getTicketPrice(booking.getScreening().getMovie().getTitle(),
                booking.getScreening().getRoom().getName(),
                booking.getScreening().getStartDateTime())
                * seatsToBeBooked.size());

        bookingRepository.save(booking);
    }
}
