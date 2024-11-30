package test.ticketservice.booking;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.service.*;
import com.epam.training.ticketservice.ui.command.BookingCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class BookingCommandTest {

    private final BookingService bookingService = mock(BookingService.class);
    private final ViewerService viewerService = mock(ViewerService.class);
    private final ScreeningService screeningService = mock(ScreeningService.class);
    private final MovieService movieService = mock(MovieService.class);
    private final RoomService roomService = mock(RoomService.class);
    private final PriceService priceService = mock(PriceService.class);

    private final BookingCommand underTest = new BookingCommand(bookingService, viewerService,
            screeningService, movieService, roomService, priceService);

    private final Movie movie = new Movie("title", "genre", 120);
    private final Room room = new Room("name", 10, 10);
    private final LocalDateTime start = LocalDateTime.parse("2020-10-10T10:10:10");
    private final Screening screening = new Screening(movie, room, start);

    @Test
    void testMakeBookingCreatesBooking() {
        //Given
        when(screeningService.getScreeningByParameters(movie, room, start)).thenReturn(screening);
        when(movieService.getMovieByTitle(movie.getTitle())).thenReturn(movie);
        when(roomService.getRoomByName(room.getName())).thenReturn(room);
        when(priceService.getTicketPrice(movie.getTitle(), room.getName(), start)).thenReturn(1500);
        //When
        var answer = underTest.makeBooking(movie.getTitle(), room.getName(),
                "2020-10-10 10:10", "1,1");
        //Then
        if (!answer.contains("Seats booked")) {
            fail();
        }

    }

    @Test
    void testMakeBookingFailsToCreateBooking() {

    }


}
