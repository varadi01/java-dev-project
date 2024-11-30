package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.utils.exceptions.ScreeningException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface IScreeningService {
    List<Screening> getAllScreenings();

    Screening getScreeningByParameters(Movie movie, Room room, LocalDateTime dateTime);

    void saveScreening(Screening screening) throws ScreeningException;

    @Transactional
    void deleteScreening(Long screeningId);
}
