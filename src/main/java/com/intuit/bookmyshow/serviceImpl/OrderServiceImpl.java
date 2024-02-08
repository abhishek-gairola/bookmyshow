package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.constant.OrderStatus;
import com.intuit.bookmyshow.entity.Order;
import com.intuit.bookmyshow.entity.Seat;
import com.intuit.bookmyshow.exception.BookingException;
import com.intuit.bookmyshow.repository.MovieScreenRepository;
import com.intuit.bookmyshow.repository.OrderRepository;
import com.intuit.bookmyshow.repository.SeatRepository;
import com.intuit.bookmyshow.request.OrderRequest;
import com.intuit.bookmyshow.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private MovieScreenRepository movieScreenRepository;

    @Override
    @Transactional
    public UUID createOrder(OrderRequest request){
        UUID bookingId = UUID.randomUUID();
        UUID movieScreenId = request.getMovieScreenId();
        List<UUID> seats = request.getSeatId();
        if(checkIfSeatsCanBeBooked(seats)){
            List<Order> orders = new ArrayList<>();
            for (UUID seatId : seats) {
                Order order = new Order();
                order.setBookingId(bookingId);
                order.setUserId(request.getUserId());
                order.setSeatId(seatId);
                order.setMovieScreenId(movieScreenId);
                order.setOrderStatus(OrderStatus.RESERVED);
                orders.add(order);
            }
            try {
                orderRepository.saveAll(orders);
                return bookingId;
            } catch (DataIntegrityViolationException ex) {
                log.error("Seats cannot be booked.");
                throw new OptimisticLockingFailureException("Optimistic locking failed", ex);
            }
        }
        else{
            log.error("Seats cannot be booked.");
            throw new BookingException("Seats cannot be booked");
        }
    }

    public boolean checkIfSeatsCanBeBooked(List<UUID> seatIds) {
        List<Order> orders = orderRepository.findBySeatIdInAndOrderStatusIn(seatIds, Arrays.asList(OrderStatus.BOOKED, OrderStatus.RESERVED));
        return orders.isEmpty();
    }
}
