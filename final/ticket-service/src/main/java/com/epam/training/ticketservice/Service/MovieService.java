package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie(String movieTitle) {
        movieRepository.deleteByTitle(movieTitle);
    }
}