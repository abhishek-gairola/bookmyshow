package com.intuit.bookmyshow.repository;

import com.intuit.bookmyshow.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends CrudRepository<City, UUID> {
}
