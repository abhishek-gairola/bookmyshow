package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.entity.Movie;
import com.intuit.bookmyshow.entity.MovieScreen;
import com.intuit.bookmyshow.repository.MovieRepository;
import com.intuit.bookmyshow.repository.MovieScreenRepository;
import com.intuit.bookmyshow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieScreenRepository movieScreenRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    @Override
    public List<Movie> getAllMoviesForCity(UUID id){
        return this.movieScreenRepository.findUniqueMoviesByCityId(id);
    }

    @Override
    public List<Movie> getAllMoviesForTheatre(UUID id){
        return this.movieScreenRepository.findUniqueMoviesByTheatreId(id);
    }
}
