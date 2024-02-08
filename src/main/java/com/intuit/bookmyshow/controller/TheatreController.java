package com.intuit.bookmyshow.controller;

import com.intuit.bookmyshow.entity.Screen;
import com.intuit.bookmyshow.entity.Theatre;
import com.intuit.bookmyshow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("/")
    public ResponseEntity<Theatre> createTheatre(@RequestParam UUID cityId, @RequestBody Theatre theatre) {
        Theatre createdTheatre = theatreService.createTheatre(cityId, theatre);
        return new ResponseEntity<>(createdTheatre, HttpStatus.CREATED);
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<Object> getTheatreById(@PathVariable UUID theatreId){
        Theatre theatre = theatreService.getTheatre(theatreId);
        if(theatre == null){
            return new ResponseEntity<>("Theatre Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(theatre, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable UUID id, @RequestBody Theatre theatre) {
        Theatre updatedTheatre = theatreService.updateTheatre(id, theatre);
        if (updatedTheatre != null) {
            return new ResponseEntity<>(updatedTheatre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/screens")
    public ResponseEntity<Theatre> addScreenToTheatre(@PathVariable UUID id, @RequestBody Screen screen) {
        Theatre updatedTheatre = theatreService.addScreenToTheatre(id, screen);
        if (updatedTheatre != null) {
            return new ResponseEntity<>(updatedTheatre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
