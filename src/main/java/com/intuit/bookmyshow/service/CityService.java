package com.intuit.bookmyshow.service;
import com.intuit.bookmyshow.entity.City;

import java.util.UUID;

public interface CityService {
    City createCity(City city) throws Exception;
    City updateCity(UUID id,  City city);
    boolean deleteCity(UUID id);
}
