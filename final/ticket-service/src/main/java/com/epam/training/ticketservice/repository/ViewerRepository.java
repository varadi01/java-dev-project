package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, String> {

    Optional<Viewer> findByUsername(String username);
}
