package com.epam.training.ticketservice.Repository;

import com.epam.training.ticketservice.Entity.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, String > {

    Optional<Viewer> findByUsername(String username);
}
