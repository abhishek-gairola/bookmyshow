package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.entity.Movie;
import com.intuit.bookmyshow.entity.MovieScreen;
import com.intuit.bookmyshow.entity.Screen;
import com.intuit.bookmyshow.repository.MovieRepository;
import com.intuit.bookmyshow.repository.MovieScreenRepository;
import com.intuit.bookmyshow.repository.ScreenRepository;
import com.intuit.bookmyshow.request.ScheduleRequest;
import com.intuit.bookmyshow.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private MovieScreenRepository movieScreenRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public MovieScreen createSchedule(ScheduleRequest scheduleRequest){
        Movie movie = movieRepository.findById(scheduleRequest.movieId).orElse(null);
        Screen screen = screenRepository.findById(scheduleRequest.screenId).orElse(null);

        if(movie != null && screen!=null){
            List<MovieScreen> existingSchedules = movieScreenRepository.findByMovieIdAndScreenId(movie.getId(), screen.getId());
            for (MovieScreen existingSchedule : existingSchedules) {
                if (isOverlap(existingSchedule, scheduleRequest)) {
                    return null;
                }
            }

            // If no overlap, create a new MovieScreen entity and save it
            MovieScreen movieScreen = new MovieScreen();
            movieScreen.setMovieId(movie.getId());
            movieScreen.setScreenId(screen.getId());
            movieScreen.setStartDate(scheduleRequest.startTime);
            movieScreen.setEndDate(scheduleRequest.endTime);
            return movieScreenRepository.save(movieScreen);
        }
        return null;
    }
    private boolean isOverlap(MovieScreen existingSchedule, ScheduleRequest newSchedule) {
        LocalDateTime existingStart = existingSchedule.getStartDate();
        LocalDateTime existingEnd = existingSchedule.getEndDate();
        LocalDateTime newStart = newSchedule.startTime;
        LocalDateTime newEnd = newSchedule.endTime;

        return (newStart.isBefore(existingEnd) || newStart.isEqual(existingEnd)) &&
                (newEnd.isAfter(existingStart) || newEnd.isEqual(existingStart));
    }
}
