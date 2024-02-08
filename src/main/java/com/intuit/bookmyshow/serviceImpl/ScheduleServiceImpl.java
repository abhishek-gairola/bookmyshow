package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.constant.OrderStatus;
import com.intuit.bookmyshow.entity.Movie;
import com.intuit.bookmyshow.entity.MovieScreen;
import com.intuit.bookmyshow.entity.Screen;
import com.intuit.bookmyshow.entity.Seat;
import com.intuit.bookmyshow.exception.ScheduleCreationException;
import com.intuit.bookmyshow.repository.MovieRepository;
import com.intuit.bookmyshow.repository.MovieScreenRepository;
import com.intuit.bookmyshow.repository.OrderRepository;
import com.intuit.bookmyshow.repository.ScreenRepository;
import com.intuit.bookmyshow.request.ScheduleRequest;
import com.intuit.bookmyshow.service.ScheduleService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private MovieScreenRepository movieScreenRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Seat> getAvailableSeats(UUID movieScreenId) {
        log.info("Fetching available seats for movie screen: {}", movieScreenId);
        MovieScreen movieScreen = movieScreenRepository.findById(movieScreenId).orElse(null);
        Screen screen = (movieScreen != null) ? screenRepository.findById(movieScreen.getScreenId()).orElse(null) : null;
        if (movieScreen == null || screen == null) {
            Collections.emptyList();
        }
        List<UUID> allSeats = screen.getSeats().stream()
                .map(seat -> seat.getId())
                .collect(Collectors.toList());
        List<UUID> bookedSeats = orderRepository.findBySeatIdInAndOrderStatusIn(allSeats, Arrays.asList(OrderStatus.BOOKED, OrderStatus.RESERVED)).stream()
                .map(order -> order.getSeatId()).collect(Collectors.toList());
        allSeats.removeAll(bookedSeats);
        List<Seat> availableSeats = screen.getSeats().stream()
                .filter(seat -> allSeats.contains(seat.getId()))
                .collect(Collectors.toList());
        log.info("Available seats fetched successfully.");
        return availableSeats;
    }
    @Override
    public MovieScreen createSchedule(ScheduleRequest scheduleRequest){
        Movie movie = movieRepository.findById(scheduleRequest.movieId).orElse(null);
        Screen screen = screenRepository.findById(scheduleRequest.screenId).orElse(null);

        if(movie != null && screen!=null){
            List<MovieScreen> existingSchedules = movieScreenRepository.findByMovieIdAndScreenId(movie.getId(), screen.getId());
            for (MovieScreen existingSchedule : existingSchedules) {
                if (isOverlap(existingSchedule, scheduleRequest)) {
                    throw new ScheduleCreationException("Failed to create a schedule: Schedule overlaps with existing schedule.");
                }
            }
            MovieScreen movieScreen = new MovieScreen();
            movieScreen.setMovieId(movie.getId());
            movieScreen.setScreenId(screen.getId());
            movieScreen.setStartDate(scheduleRequest.startTime);
            movieScreen.setEndDate(scheduleRequest.endTime);
            return movieScreenRepository.save(movieScreen);
        }
        throw new ScheduleCreationException("Failed to create a schedule: Invalid movie or screen ID.");
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
