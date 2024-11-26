package com.epam.training.ticketservice.Repository;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Entity.Room;
import com.epam.training.ticketservice.Entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    //TODO TEST
    public Screening findScreeningByRoomAndMovieAndStartDateTime(Room room, Movie movie, LocalDateTime startDate);

    public List<Screening> findScreeningByRoom(Room room);
}
