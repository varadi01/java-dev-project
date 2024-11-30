package test.ticketservice.booking;

import com.epam.training.ticketservice.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingEntityTest {

    @Test
    void testEquals() {
        //Given
        var viewer = new Viewer("username", "password");
        var movie = new Movie("title", "genre", 120);
        var room = new Room("name", 10, 10);
        var start = LocalDateTime.parse("2020-01-01T00:00:00");
        var screening = new Screening(movie, room, start);
        //When
        var booking = new Booking(viewer, screening, List.of("1,1"), 1500);
        var otherBooking = new Booking(viewer, screening, List.of("1,1"), 1500);
        //Then
        assertEquals(booking, otherBooking);
        assertTrue(booking.equals(otherBooking));
        otherBooking.setPrice(1000);
        assertFalse(booking.equals(otherBooking));
    }
}
