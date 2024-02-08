package com.intuit.bookmyshow.controller;

import com.intuit.bookmyshow.entity.MovieScreen;
import com.intuit.bookmyshow.request.OrderRequest;
import com.intuit.bookmyshow.request.ScheduleRequest;
import com.intuit.bookmyshow.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/booking")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<UUID> createOrder(@RequestBody OrderRequest request) {
        UUID bookingId = orderService.createOrder(request);
        return new ResponseEntity<>(bookingId, HttpStatus.CREATED);
    }
}
