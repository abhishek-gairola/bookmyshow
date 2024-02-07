package com.intuit.bookmyshow.controller;

import com.intuit.bookmyshow.entity.Movie;
import com.intuit.bookmyshow.entity.MovieScreen;
import com.intuit.bookmyshow.request.ScheduleRequest;
import com.intuit.bookmyshow.service.MovieService;
import com.intuit.bookmyshow.service.ScheduleService;
import com.intuit.bookmyshow.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/")
    public ResponseEntity<MovieScreen> scheduleMovie(@RequestBody ScheduleRequest request) {
        MovieScreen movieScreen = scheduleService.createSchedule(request);
        return new ResponseEntity<>(movieScreen, HttpStatus.CREATED);
    }
    // Update Schedule using movieScreeId

}
