package com.intuit.bookmyshow.entity;

import jakarta.persistence.*;
import com.intuit.bookmyshow.constant.State;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    private String name;
    private State state;
    private String description;
    private double latitude;
    private double longitude;

}
