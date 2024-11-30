package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    public Screening findScreeningByRoomAndMovieAndStartDateTime(Room room, Movie movie, LocalDateTime startDate);

    public List<Screening> findScreeningByRoom(Room room);
}
