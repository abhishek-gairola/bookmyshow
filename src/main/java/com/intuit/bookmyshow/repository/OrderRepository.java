package com.intuit.bookmyshow.repository;

import com.intuit.bookmyshow.constant.OrderStatus;
import com.intuit.bookmyshow.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
    List<Order> findBySeatIdInAndOrderStatusIn(List<UUID> seatIds,List<OrderStatus> statusList);
}
