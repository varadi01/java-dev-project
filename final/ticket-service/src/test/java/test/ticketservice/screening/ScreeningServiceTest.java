package test.ticketservice.screening;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.utils.exceptions.ScreeningException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

public class ScreeningServiceTest {

    private final ScreeningRepository screeningRepository = mock(ScreeningRepository.class);
    private final ScreeningService underTest = new ScreeningService(screeningRepository);

    private static final Movie movie1 = new Movie("title1", "genre1", 120);
    private static final Movie movie2 = new Movie("title2", "genre2", 130);

    private static final Room room1 = new Room("room1", 10, 20);
    private static final Room room2 = new Room("room2", 12, 18);

    private static final Screening screening1 = new Screening(movie1, room1, LocalDateTime.parse("2010-11-02T12:00"));
    private static final Screening screening2 = new Screening(movie2, room2, LocalDateTime.parse("2010-11-02T12:00"));
    private static final Screening screening3 = new Screening(movie2, room1, LocalDateTime.parse("2010-11-02T17:00"));

    @Test
    void testGetAllScreeningsShouldReturnAllScreenings() {
        //Given
        when(screeningRepository.findAll()).thenReturn(List.of(screening1, screening2));
        List<Screening> expected = List.of(screening1, screening2);
        //When
        List<Screening> actual = underTest.getAllScreenings();
        //Then
        assertEquals(expected, actual);
        verify(screeningRepository).findAll();
    }

    @Test
    void testGetScreeningByParametersShouldReturnScreening() {
        //Given
        var time = LocalDateTime.now();
        when(screeningRepository.findScreeningByRoomAndMovieAndStartDateTime(room1, movie1, time)).thenReturn(screening1);
        Screening expected = screening1;
        //When
        Screening actual = underTest.getScreeningByParameters(movie1, room1, time);
        //Then
        assertEquals(expected, actual);
        verify(screeningRepository).findScreeningByRoomAndMovieAndStartDateTime(room1, movie1, time);
    }

    @Test
    void testGetScreeningByParametersShouldReturnNullWhenNoSuchScreeningExists() {
        //Given
        var time = LocalDateTime.now();
        when(screeningRepository.findScreeningByRoomAndMovieAndStartDateTime(room2, movie1, time)).thenReturn(null);
        //When
        Screening actual = underTest.getScreeningByParameters(movie1, room2, time);
        //Then
        assertNull(actual);
        verify(screeningRepository).findScreeningByRoomAndMovieAndStartDateTime(room2, movie1, time);
    }

    @Test
    void testSaveScreeningShouldSaveScreening() {
        //Given
        when(screeningRepository.findScreeningByRoom(room1)).thenReturn(List.of(screening3));
        when(screeningRepository.save(screening1)).thenReturn(screening1);
        //When
        try {
            underTest.saveScreening(screening1);
        } catch (ScreeningException e) {
            fail();
        }
        //Then
        verify(screeningRepository).save(screening1);
        verify(screeningRepository).findScreeningByRoom(room1);
    }

    @Test
    void testSaveScreeningShouldNotSaveScreeningWhenItOverlapsWithAnotherScreening() {
        //Given
        var overlappingScreening = new Screening(movie1, room1, LocalDateTime.parse("2010-11-02T13:00"));
        when(screeningRepository.findScreeningByRoom(room1)).thenReturn(List.of(screening3, screening1));
        when(screeningRepository.save(overlappingScreening)).thenReturn(overlappingScreening);
        //When
        assertThrows(ScreeningException.class, () -> underTest.saveScreening(overlappingScreening));
        //Then
        verify(screeningRepository, never()).save(overlappingScreening);
    }

    @Test
    void testSaveScreeningShouldNotSaveScreeningWhenItStartsWithinTenMinutesOfAnotherScreening() {
        //Given
        var overlappingScreening = new Screening(movie1, room1, LocalDateTime.parse("2010-11-02T14:05"));
        when(screeningRepository.findScreeningByRoom(room1)).thenReturn(List.of(screening3, screening1));
        when(screeningRepository.save(overlappingScreening)).thenReturn(overlappingScreening);
        //When
        assertThrows(ScreeningException.class, () -> underTest.saveScreening(overlappingScreening));
        //Then
        verify(screeningRepository, never()).save(overlappingScreening);
    }
}
