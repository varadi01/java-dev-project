package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Booking;
import com.epam.training.ticketservice.Repository.BookingRepository;
import com.epam.training.ticketservice.Utils.Exceptions.BookingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByViewer(String viewerName) {
        return bookingRepository.findAllByViewerUsername(viewerName);
    }

    public void saveBooking(Booking booking) throws BookingException {
        //TODO seat taken, not exist
        bookingRepository.save(booking);
    }
}
