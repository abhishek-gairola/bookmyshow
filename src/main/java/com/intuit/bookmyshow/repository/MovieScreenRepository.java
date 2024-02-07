package com.intuit.bookmyshow.repository;

import com.intuit.bookmyshow.entity.Movie;
import com.intuit.bookmyshow.entity.MovieScreen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieScreenRepository extends CrudRepository<MovieScreen, UUID> {
    List<MovieScreen> findByMovieIdAndScreenId(UUID movieId, UUID screenId);

    @Query("SELECT DISTINCT m FROM Movie m " +
            "JOIN MovieScreen ms ON m.id = ms.movieId " +
            "JOIN Screen s ON ms.screenId = s.id " +
            "JOIN Theatre t ON s.theatre.id = t.id " +
            "JOIN City c ON t.city.id = c.id " +
            "WHERE c.id = ?1")
    List<Movie> findUniqueMoviesByCityId(UUID cityId);

    @Query("SELECT DISTINCT m FROM Movie m " +
            "JOIN MovieScreen ms ON m.id = ms.movieId " +
            "JOIN Screen s ON ms.screenId = s.id " +
            "JOIN Theatre t ON s.theatre.id = t.id " +
            "WHERE t.id = ?1")
    List<Movie> findUniqueMoviesByTheatreId(UUID cityId);

}
