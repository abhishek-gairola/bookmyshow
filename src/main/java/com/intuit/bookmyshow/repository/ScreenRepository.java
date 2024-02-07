package com.intuit.bookmyshow.repository;

import com.intuit.bookmyshow.entity.Screen;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ScreenRepository extends CrudRepository<Screen, UUID> {
}
