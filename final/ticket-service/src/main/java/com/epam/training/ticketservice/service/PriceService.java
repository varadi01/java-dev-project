package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.PriceComponent;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.PriceComponentRepository;
import com.epam.training.ticketservice.utils.PriceComponentAttachable;
import com.epam.training.ticketservice.utils.singletonvalues.SingletonValue;
import com.epam.training.ticketservice.utils.singletonvalues.SingletonValueService;
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
                        MovieService movieService, ScreeningService screeningService, SingletonValueService svs) {
        this.priceComponentRepository = priceComponentRepository;

        this.roomService = roomService;
        this.movieService = movieService;
        this.screeningService = screeningService;
        this.singletonValueService = svs;
    }

    public void updateBasePrice(int newValue) {
        singletonValueService.updateSingletonValue(new SingletonValue("base_price", String.valueOf(newValue)));
    }

    public void savePriceComponent(PriceComponent priceComponent) {
        priceComponentRepository.save(priceComponent);
    }

    public void attachPriceComponent(PriceComponent priceComponent, PriceComponentAttachable target) {
        target.setPriceComponent(priceComponent); //TODO not working
    }

    public PriceComponent getPriceComponentByName(String name) {
        return priceComponentRepository.findByName(name);
    }

    public int getTicketPrice(String movieTitle, String roomName, LocalDateTime startDateTime) {
        int totalPrice = Integer.parseInt(singletonValueService.getSingletonValueByName("base_price").getSingleValue());

        Room room = roomService.getRoomByName(roomName);
        Movie movie = movieService.getMovieByTitle(movieTitle);
        Screening screening = screeningService.getScreeningByParameters(movie, room, startDateTime);

        totalPrice += room.getPriceComponent().getComponentValue()
                +
                movie.getPriceComponent().getComponentValue()
                +
                screening.getPriceComponent().getComponentValue();

        return totalPrice;
    }
}
