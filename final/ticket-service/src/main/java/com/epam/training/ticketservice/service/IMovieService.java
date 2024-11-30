package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Movie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();

    Movie getMovieByTitle(String title);

    void saveMovie(Movie movie);

    void updateMovie(Movie movie);

    @Transactional
    void deleteMovie(String movieTitle);
}
