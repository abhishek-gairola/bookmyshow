package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.entity.City;
import com.intuit.bookmyshow.repository.CityRepository;
import com.intuit.bookmyshow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(UUID id, City updatedCity) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            city.setName(updatedCity.getName());
            city.setState(updatedCity.getState());
            city.setDescription(updatedCity.getDescription());
            city.setLatitude(updatedCity.getLatitude());
            city.setLongitude(updatedCity.getLongitude());
            return cityRepository.save(city);
        } else {
            return null;
        }
    }

    public boolean deleteCity(UUID id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            cityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
