package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.PriceComponent;
import com.epam.training.ticketservice.utils.PriceComponentAttachable;

import java.time.LocalDateTime;

public interface IPriceService {
    void updateBasePrice(int newValue);

    void savePriceComponent(PriceComponent priceComponent);

    void attachPriceComponent(PriceComponent priceComponent, PriceComponentAttachable target);

    PriceComponent getPriceComponentByName(String name);

    int getTicketPrice(String movieTitle, String roomName, LocalDateTime startDateTime);
}
