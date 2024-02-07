package com.intuit.bookmyshow.controller;

import com.intuit.bookmyshow.entity.City;
import com.intuit.bookmyshow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/")
    public ResponseEntity<City> createCity(@RequestBody City city) throws Exception {
        City createdCity = cityService.createCity(city);
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable UUID id, @RequestBody City city) {
        City updatedCity = cityService.updateCity(id, city);
        if (updatedCity != null) {
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable UUID id) {
        boolean deleted = cityService.deleteCity(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
