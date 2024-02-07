package com.intuit.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intuit.bookmyshow.constant.MovieRatings;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @ManyToOne // Many theatres  can be associated with one city
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    @Column(nullable = true)
    private String address;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @OneToMany(targetEntity = Screen.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "theatre_id")
    private List<Screen> screenList;
}
