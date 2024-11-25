package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void SaveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void UpdateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(String movieTitle) {
        movieRepository.deleteByTitle(movieTitle);
    }
}
