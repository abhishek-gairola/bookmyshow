package com.intuit.bookmyshow.entity;

import com.intuit.bookmyshow.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    private UUID movieScreenId;
    private UUID seatId;
    private UUID userId;
    private UUID bookingId;
    private OrderStatus orderStatus;

}
