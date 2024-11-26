package com.epam.training.ticketservice.Repository;

import com.epam.training.ticketservice.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    public Movie findByTitle(String title);

    public void deleteByTitle(String title);
}
