package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Entity.Room;
import com.epam.training.ticketservice.Entity.Screening;
import com.epam.training.ticketservice.Repository.ScreeningRepository;
import com.epam.training.ticketservice.Utils.Exceptions.ScreeningException;
import org.springframework.stereotype.Service;

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

    public Screening getScreeningByParameters(Room room, Movie movie, LocalDateTime dateTime) {
        return screeningRepository.findScreeningByRoomAndMovieAndStartDateTime(room, movie, dateTime);
    }

    public void saveScreening(Screening screening) throws ScreeningException {
        var existingScreenings =  screeningRepository.findScreeningByRoom(screening.getRoom());

        var startTime = screening.getStartDateTime();
        var endTime = screening.getStartDateTime().plusMinutes(screening.getMovie().getLengthInMinutes());

        for (Screening existingScreening : existingScreenings) {
            var exStartTime = existingScreening.getStartDateTime();
            var exEndTime = existingScreening.getStartDateTime().plusMinutes(screening.getMovie().getLengthInMinutes());

            if (exStartTime.isAfter(startTime) && exEndTime.isBefore(endTime) ||
                exStartTime.isBefore(startTime) && exEndTime.isAfter(startTime) ||
                exStartTime.isBefore(endTime) && exEndTime.isAfter(endTime) ||
                exStartTime.isBefore(startTime) && exEndTime.isAfter(endTime) ){

                throw new ScreeningException("There is an overlapping screening");
            }

            if (exEndTime.plusMinutes(10).isAfter(startTime)){
                throw new ScreeningException("This would start in the break period after another screening in this room");
            }
        }

        screeningRepository.save(screening);
    }

    public void deleteScreening(Long screeningId) {
        screeningRepository.deleteById(screeningId);
    }
}