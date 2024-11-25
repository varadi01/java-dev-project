package com.epam.training.ticketservice.Repository;

import com.epam.training.ticketservice.Entity.PriceComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceComponentRepository extends JpaRepository<PriceComponent, String> {
}
