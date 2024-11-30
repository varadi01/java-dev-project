package test.ticketservice.movie;

import com.epam.training.ticketservice.entity.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieEntityTest {

    @Test
    void testMovieGettersSetters() {
        //Given
        var title = "title";
        var genre = "genre";
        var length = 120;
        //When
        Movie movie = new Movie(title, genre, length);
        //Then
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(length, movie.getLengthInMinutes());

        //Setters
        //Given
        title = "new title";
        genre = "new genre";
        length = 150;
        //When
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setLengthInMinutes(length);
        //Then
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(length, movie.getLengthInMinutes());
    }

    @Test
    void testMovieEquals() {
        //Given
        var title = "title";
        var genre = "genre";
        var length = 120;
        //When
        Movie movie = new Movie(title, genre, length);
        Movie otherMovie = new Movie(title, genre, length);
        //Then
        assertEquals(movie, otherMovie);
        assertTrue(movie.equals(movie));
        assertTrue(movie.equals(otherMovie));

        otherMovie.setTitle("another title");
        assertFalse(movie.equals(otherMovie));
        assertFalse(movie.equals(null));
    }
}
