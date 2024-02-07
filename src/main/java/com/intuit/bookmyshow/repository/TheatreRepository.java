package com.intuit.bookmyshow.repository;

import com.intuit.bookmyshow.entity.Theatre;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TheatreRepository extends CrudRepository<Theatre, UUID> {
}
