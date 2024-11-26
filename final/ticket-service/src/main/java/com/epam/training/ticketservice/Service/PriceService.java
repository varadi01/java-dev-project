package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Entity.PriceComponent;
import com.epam.training.ticketservice.Entity.Room;
import com.epam.training.ticketservice.Entity.Screening;
import com.epam.training.ticketservice.Repository.PriceComponentRepository;
import com.epam.training.ticketservice.Utils.PriceComponentAttachable;
import com.epam.training.ticketservice.Utils.SingletonValues.SingletonValue;
import com.epam.training.ticketservice.Utils.SingletonValues.SingletonValueService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceComponentRepository priceComponentRepository;

    private final RoomService roomService;
    private final MovieService movieService;
    private final ScreeningService screeningService;

    private final SingletonValueService singletonValueService;

    public PriceService(PriceComponentRepository priceComponentRepository, RoomService roomService,
                        MovieService movieService, ScreeningService screeningService, SingletonValueService singletonValueService) {
        this.priceComponentRepository = priceComponentRepository;

        this.roomService = roomService;
        this.movieService = movieService;
        this.screeningService = screeningService;
        this.singletonValueService = singletonValueService;
    }

    public void updateBasePrice(int newValue){
        singletonValueService.updateSingletonValue(new SingletonValue("base_price", String.valueOf(newValue)));
    }

    public void savePriceComponent(PriceComponent priceComponent){
        priceComponentRepository.save(priceComponent);
    }

    public <T extends PriceComponentAttachable> void attachPriceComponent(PriceComponent priceComponent, T target){
        //TODO test
        target.setPriceComponent(priceComponent);
    }

    public int getTicketPrice(String movieTitle, String roomName, LocalDateTime startDateTime){
        int totalPrice = Integer.parseInt(singletonValueService.getSingletonValueByName("base_price").getValue());

        //meh
        Room room = roomService.getRoomByName(roomName);
        Movie movie = movieService.getMovieByTitle(movieTitle);
        Screening screening = screeningService.getScreeningByParameters(room, movie, startDateTime);

        //TODO i did something but it still might be nullreference
        totalPrice += room.getPriceComponent().getValue() +
                movie.getPriceComponent().getValue() +
                screening.getPriceComponent().getValue();

        return totalPrice;
    }
}
