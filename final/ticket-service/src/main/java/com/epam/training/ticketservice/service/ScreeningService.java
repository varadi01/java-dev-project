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
public class ScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    public Screening getScreeningByParameters(Movie movie, Room room, LocalDateTime dateTime) {
        return screeningRepository.findScreeningByRoomAndMovieAndStartDateTime(room, movie, dateTime);
    }

    public void saveScreening(Screening screening) throws ScreeningException {
        var existingScreenings = screeningRepository.findScreeningByRoom(screening.getRoom());

        var startTime = screening.getStartDateTime();
        var endTime = screening.getStartDateTime().plusMinutes(screening.getMovie().getLengthInMinutes());

        for (Screening existingScreening : existingScreenings) {
            var exStartTime = existingScreening.getStartDateTime();
            var exEndTime = existingScreening.getStartDateTime().plusMinutes(screening.getMovie().getLengthInMinutes());

            if (exStartTime.isAfter(startTime) && exEndTime.isBefore(endTime) // [ ( ) ]
                    ||
                    exStartTime.isBefore(startTime) && exEndTime.isBefore(endTime) // ( [ ) ]
                    ||
                    exStartTime.isBefore(endTime) && exEndTime.isAfter(endTime) // [ ( ] )
                    ||
                    exStartTime.isBefore(startTime) && exEndTime.isAfter(endTime)) { // ( [ ] )

                throw new ScreeningException("There is an overlapping screening");
            }

            if (exEndTime.plusMinutes(11).isAfter(startTime)) {
                throw new ScreeningException("This would start in the break period after another screening in this room");
            }
        }

        screeningRepository.save(screening);
    }

    @Transactional
    public void deleteScreening(Long screeningId) {
        screeningRepository.deleteById(screeningId);
    }
}