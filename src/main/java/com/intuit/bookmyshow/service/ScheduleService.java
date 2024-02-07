package com.intuit.bookmyshow.service;

import com.intuit.bookmyshow.entity.MovieScreen;
import com.intuit.bookmyshow.request.ScheduleRequest;

public interface ScheduleService {
    MovieScreen createSchedule(ScheduleRequest request);
}
