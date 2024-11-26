package com.epam.training.ticketservice.Repository;

import com.epam.training.ticketservice.Entity.Booking;
import com.epam.training.ticketservice.Entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    public List<Booking> findAllByViewerUsername(String viewerName); //TODO TEST

    public List<Booking> findAllByScreening(Screening screening);
}
