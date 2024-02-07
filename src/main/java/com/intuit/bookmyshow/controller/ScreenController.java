package com.intuit.bookmyshow.controller;

import com.intuit.bookmyshow.entity.Screen;
import com.intuit.bookmyshow.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/screen")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @PostMapping("/{theatreId}")
    public ResponseEntity<Screen> createScreen(@PathVariable UUID theatreId, @RequestBody List<String> seatNames) {
        Screen createdScreen = screenService.createScreen(theatreId, seatNames);
        if (createdScreen != null) {
            return new ResponseEntity<>(createdScreen, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{screenId}/addSeats")
    public ResponseEntity<Screen> addSeatsToScreen(@PathVariable UUID screenId, @RequestBody List<String> seatNames) {
        Screen updatedScreen = screenService.addSeatsToScreen(screenId, seatNames);
        if (updatedScreen != null) {
            return new ResponseEntity<>(updatedScreen, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{screenId}")
    public ResponseEntity<Screen> updateScreen(@PathVariable UUID screenId, @RequestBody Screen screen) {
        Screen updatedScreen = screenService.updateScreen(screenId, screen);
        if (updatedScreen != null) {
            return new ResponseEntity<>(updatedScreen, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
