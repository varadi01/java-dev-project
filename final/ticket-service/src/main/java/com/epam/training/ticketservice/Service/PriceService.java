package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.PriceComponent;
import com.epam.training.ticketservice.Repository.PriceComponentRepository;
import com.epam.training.ticketservice.Utils.PriceComponentAttachable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {
    private static final int BASE_PRICE = 1500; //temporary

    private final PriceComponentRepository priceComponentRepository;

    public PriceService(PriceComponentRepository priceComponentRepository) {
        this.priceComponentRepository = priceComponentRepository;
    }

    public void updateBasePrice(int newValue){
        //TODO
    }

    public void savePriceComponent(PriceComponent priceComponent){
        priceComponentRepository.save(priceComponent);
    }

    public <T extends PriceComponentAttachable> void attachPriceComponent(PriceComponent priceComponent, T target){
        //TODO ,test

    }

    public int getTicketPrice(String movieTitle, String roomName, LocalDateTime startDateTime){
        //TODO
        return 0;
    }
}
