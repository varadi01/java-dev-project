package test.ticketservice.booking;

import com.epam.training.ticketservice.entity.*;
import com.epam.training.ticketservice.repository.BookingRepository;
import com.epam.training.ticketservice.service.BookingService;
import com.epam.training.ticketservice.service.PriceService;
import com.epam.training.ticketservice.utils.exceptions.BookingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private final BookingRepository bookingRepository = mock(BookingRepository.class);

    private final PriceService priceService = mock(PriceService.class);
    private final BookingService underTest = new BookingService(bookingRepository, priceService);

    private final static Viewer user = new Viewer("username", "password");
    private final static Viewer user2 = new Viewer("user", "pw");

    private static final Movie movie1 = new Movie("title1", "genre1", 120);
    private static final Movie movie2 = new Movie("title2", "genre2", 130);

    private static final Room room1 = new Room("room1", 10, 20);
    private static final Room room2 = new Room("room2", 12, 18);

    private static final Screening screening1 = new Screening(movie1, room1, LocalDateTime.parse("2010-11-02T12:00"));
    private static final Screening screening2 = new Screening(movie2, room2, LocalDateTime.parse("2010-11-02T12:00"));
    private static final Screening screening3 = new Screening(movie2, room1, LocalDateTime.parse("2010-11-02T17:00"));


    private final Booking booking1 = new Booking(user, screening1, List.of("1,1"), 1500);

    @Test
    void testGetAllBookingsReturnsAllBookings() {
        //Given
        when(bookingRepository.findAll()).thenReturn(List.of(booking1));
        List<Booking> expected = List.of(booking1);
        //When
        var actual = underTest.getAllBookings();
        //Then
        assertEquals(expected, actual);
        verify(bookingRepository).findAll();
    }

    @Test
    void testGetAllBookingsByViewerReturnsAllBookings() {
        //Given
        when(bookingRepository.findAllByViewerUsername(user.getUsername())).thenReturn(List.of(booking1));
        List<Booking> expected = List.of(booking1);
        //When
        var actual = underTest.getBookingsByViewer(user.getUsername());
        //Then
        assertEquals(expected, actual);
        verify(bookingRepository).findAllByViewerUsername(user.getUsername());
    }

    @Test
    void testSaveBookingSavesBooking() {
        //Given
        when(bookingRepository.save(booking1)).thenReturn(booking1);
        when(bookingRepository.findAllByScreening(screening1)).thenReturn(List.of());
        //When
        try {
            underTest.saveBooking(booking1);
        } catch (BookingException e) {
            fail();
        }
        //Then
        verify(bookingRepository).save(booking1);
        verify(bookingRepository).findAllByScreening(screening1);
    }

    @Test
    void testSaveBookingDoesNotSaveBookingWhenSeatDoesNotExist() {
        //Given
        when(bookingRepository.save(booking1)).thenReturn(booking1);
        when(bookingRepository.findAllByScreening(screening1)).thenReturn(List.of());
        //When
        try {
            underTest.saveBooking(new Booking(user, screening1, List.of("1,30"), 1500));
            fail();
        } catch (BookingException e) {

        }
    }

    @Test
    void testSaveBookingDoesNotSaveBookingWhenSeatIsTaken() {
        //Given
        when(bookingRepository.save(booking1)).thenReturn(booking1);
        when(bookingRepository.findAllByScreening(screening1)).thenReturn(List.of(booking1));
        //When
        try {
            underTest.saveBooking(new Booking(user, screening1, List.of("1,1", "1,2"), 1500));
            fail();
        } catch (BookingException e) {

        }
    }
}
