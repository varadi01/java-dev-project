package test.ticketservice.price;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.PriceComponentRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.PriceService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.utils.singletonvalues.SingletonValue;
import com.epam.training.ticketservice.utils.singletonvalues.SingletonValueService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PriceServiceTest {

    private final PriceComponentRepository priceComponentRepository = mock(PriceComponentRepository.class);

    private final RoomService roomService = mock(RoomService.class);
    private final MovieService movieService = mock(MovieService.class);
    private final ScreeningService screeningService = mock(ScreeningService.class);
    private final SingletonValueService singletonValueService = mock(SingletonValueService.class);
    private final PriceService underTest = new PriceService(priceComponentRepository, roomService,
            movieService, screeningService, singletonValueService);

    private static final Movie movie1 = new Movie("title1", "genre1", 120);

    private static final Room room1 = new Room("room1", 10, 20);

    private static final LocalDateTime start = LocalDateTime.parse("2010-11-02T12:00");
    private static final Screening screening1 = new Screening(movie1, room1, start);

    @Test
    void testUpdateBasePriceShouldUpdatePrice() {
        //When
        underTest.updateBasePrice(1000);
        //Then
        verify(singletonValueService).updateSingletonValue(new SingletonValue("base_price", "1000"));
    }

    @Test
    void testGetPriceComponentByNameShouldReturnPriceComponent() {
        //When
        underTest.getPriceComponentByName("test");
        //Then
        verify(priceComponentRepository).findByName("test");
    }

    @Test
    void testGetTicketPriceShouldReturnTicketPrice() {
        //Given
        when(roomService.getRoomByName(room1.getName())).thenReturn(room1);
        when(movieService.getMovieByTitle(movie1.getTitle())).thenReturn(movie1);
        when(screeningService.getScreeningByParameters(movie1, room1, start)).thenReturn(screening1);
        when(singletonValueService.getSingletonValueByName("base_price")).thenReturn(new SingletonValue("base_price", "1500"));
        //When
        var price = underTest.getTicketPrice("title1", "room1", start);
        //Then
        assertEquals(price, 1500);
        verify(singletonValueService).getSingletonValueByName("base_price");
    }
}
