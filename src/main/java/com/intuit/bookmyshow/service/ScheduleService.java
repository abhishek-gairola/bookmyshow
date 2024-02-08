package com.intuit.bookmyshow.service;

import com.intuit.bookmyshow.entity.MovieScreen;
import com.intuit.bookmyshow.entity.Seat;
import com.intuit.bookmyshow.request.ScheduleRequest;

import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    MovieScreen createSchedule(ScheduleRequest request);
    List<Seat> getAvailableSeats(UUID movieScreenId);
}
