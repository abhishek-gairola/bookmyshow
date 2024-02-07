package com.intuit.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne // Many screens can be associated with one theatre
    @JoinColumn(name = "theatre_id", nullable = false)
    @JsonIgnore
    private Theatre theatre;

    @OneToMany(mappedBy = "screenId", cascade = CascadeType.ALL)
    private List<Seat> seats;

}
