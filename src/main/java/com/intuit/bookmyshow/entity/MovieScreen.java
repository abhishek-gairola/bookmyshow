package com.intuit.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MovieScreen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(name = "movieId", nullable = false)
    private UUID movieId;

    @Column(name = "screenId", nullable = false)
    private UUID screenId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
