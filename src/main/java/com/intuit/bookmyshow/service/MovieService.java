package com.intuit.bookmyshow.service;

import com.intuit.bookmyshow.entity.Movie;

import java.util.List;
import java.util.UUID;

public interface MovieService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMoviesForCity(UUID id);
    List<Movie> getAllMoviesForTheatre(UUID id);
}