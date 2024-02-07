package com.intuit.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @JoinColumn(nullable = false)
    private UUID screenId;

    @Column(nullable = false)
    private String seatNumber; // You can customize this based on your seat naming convention (e.g., 1A, 2A, 1B, K23, etc.)

}