package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.Booking;
import com.epam.training.ticketservice.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    public List<Booking> findAllByViewerUsername(String viewerName);

    public List<Booking> findAllByScreening(Screening screening);
}
