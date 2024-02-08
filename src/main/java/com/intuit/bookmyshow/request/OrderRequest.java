package com.intuit.bookmyshow.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderRequest {
    private UUID movieScreenId;
    private List<UUID> seatId;
    private UUID userId;

}
