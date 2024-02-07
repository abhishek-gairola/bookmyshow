package com.intuit.bookmyshow.controller;


import com.intuit.bookmyshow.entity.Movie;
import com.intuit.bookmyshow.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @GetMapping("/city/{cityId}")
     public List<Movie> getAllMoviesForCity(@PathVariable("cityId") UUID cityId) {
        return movieService.getAllMoviesForCity(cityId);
    }

    @GetMapping("/theatre/{theatreId}")
    public List<Movie> getAllMoviesForTheatre(@PathVariable("theatreId") UUID theatreId) {
        return movieService.getAllMoviesForTheatre(theatreId);
    }
}
