package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Booking;
import com.epam.training.ticketservice.utils.exceptions.BookingException;

import java.util.List;

public interface IBookingService {

    public List<Booking> getAllBookings();

    public List<Booking> getBookingsByViewer(String viewerName);

    public void saveBooking(Booking booking) throws BookingException;
}
