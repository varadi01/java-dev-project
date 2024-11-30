package test.ticketservice.movie;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    private final MovieRepository movieRepository = mock(MovieRepository.class);
    private final MovieService underTest = new MovieService(movieRepository);

    private static final Movie movie1 = new Movie("title1", "genre1", 120);
    private static final Movie movie2 = new Movie("title2", "genre2", 130);

    @Test
    void testGetAllMoviesShouldReturnListOfMovies() {
        //Given
        when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2));
        List<Movie> expected = List.of(movie1, movie2);
        //When
        List<Movie> actual = underTest.getAllMovies();
        //Then
        assertEquals(expected, actual);
        verify(movieRepository).findAll();
    }

    @Test
    void testGetMovieByTitleShouldReturnMovie() {
        //Given
        when(movieRepository.findByTitle("title1")).thenReturn(movie1);
        Movie expected = movie1;
        //When
        Movie actual = underTest.getMovieByTitle("title1");
        //Then
        assertEquals(expected, actual);
        verify(movieRepository).findByTitle("title1");
    }

    @Test
    void testGetMovieByTitleShouldReturnNullWhenMovieDoesNotExists() {
        //Given
        when(movieRepository.findByTitle("title3")).thenReturn(null);
        //When
        Movie actual = underTest.getMovieByTitle("title3");
        //Then
        assertNull(actual);
        verify(movieRepository).findByTitle("title3");
    }

    @Test
    void testSaveMovieShouldSaveMovie() {
        //Given
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie2);
        when(movieRepository.save(movie1)).thenReturn(movies.add(movie1) ? movie1 : null);
        List<Movie> expected = List.of(movie2, movie1);
        //When
        underTest.saveMovie(movie1);
        //Then
        verify(movieRepository).save(movie1);
        assertEquals(expected, movies);
    }
}
