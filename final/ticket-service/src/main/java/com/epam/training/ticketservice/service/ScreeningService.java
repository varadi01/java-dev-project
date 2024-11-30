package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.utils.exceptions.ScreeningException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScreeningService implements IScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public Screening getScreeningByParameters(Movie movie, Room room, LocalDateTime dateTime) {
        return screeningRepository.findScreeningByRoomAndMovieAndStartDateTime(room, movie, dateTime);
    }

    @Override
    public void saveScreening(Screening screening) throws ScreeningException {
        var existingScreenings = screeningRepository.findScreeningByRoom(screening.getRoom());

        var startTime = screening.getStartDateTime();
        var endTime = screening.getStartDateTime().plusMinutes(screening.getMovie().getLengthInMinutes());

        for (Screening existingScreening : existingScreenings) {
            var exStartTime = existingScreening.getStartDateTime();
            var exEndTime = exStartTime.plusMinutes(existingScreening.getMovie().getLengthInMinutes());

            if (!(endTime.isBefore(exStartTime) || startTime.isAfter(exEndTime))) {
                throw new ScreeningException("There is an overlapping screening");
            }

            if (startTime.isAfter(exEndTime.plusMinutes(-1)) && startTime.isBefore(exEndTime.plusMinutes(10))) {
                var text = "This would start in the break period after another screening in this room";
                throw new ScreeningException(text);
            }
        }

        screeningRepository.save(screening);
    }

    @Override
    @Transactional
    public void deleteScreening(Long screeningId) {
        screeningRepository.deleteById(screeningId);
    }
}