package test.ticketservice.screening;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.PriceComponent;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ScreeningEntityTest {

    @Test
    void testGetterAndSetters() {
        //Getters
        //Given
        Movie movie =new Movie("title", "genre", 120);
        Room room = new Room("name", 10, 10);
        var start = LocalDateTime.parse("2020-10-10T10:10:10");
        //When
        Screening screening = new Screening(movie, room, start);
        //Then
        assertEquals(screening.getMovie(), movie);
        assertEquals(screening.getRoom(), room);
        assertEquals(screening.getStartDateTime(), start);
        assertEquals(screening.getPriceComponent(), new PriceComponent("_", 0));

        //Setters
        //Given
        movie.setTitle("new title");
        room.setName("new name");
        start = LocalDateTime.parse("2020-11-10T10:10:10");
        //When
        screening.setStartDateTime(start);
        screening.setMovie(movie);
        screening.setRoom(room);
        //Then
        assertEquals(screening.getMovie(), movie);
        assertEquals(screening.getRoom(), room);
        assertEquals(screening.getStartDateTime(), start);
    }

    @Test
    void testEquals() {
        //Given
        Movie movie =new Movie("title", "genre", 120);
        Room room = new Room("name", 10, 10);
        var start = LocalDateTime.parse("2020-10-10T10:10:10");
        //When
        Screening screening = new Screening(movie, room, start);
        Screening screening2 = new Screening(movie, room, start);
        //Then
        assertTrue(screening.equals(screening2));
        screening2.setMovie(new Movie("new title", "genre", 120));
        assertFalse(screening.equals(screening2));
    }
}
