package com.intuit.bookmyshow.service;

import com.intuit.bookmyshow.request.OrderRequest;

import java.util.UUID;

public interface OrderService {
    UUID createOrder(OrderRequest request);
}
