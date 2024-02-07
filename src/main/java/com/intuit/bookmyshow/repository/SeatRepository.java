package com.intuit.bookmyshow.repository;

import com.intuit.bookmyshow.entity.Seat;
import com.intuit.bookmyshow.entity.Theatre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatRepository extends CrudRepository<Seat, UUID> {
}
