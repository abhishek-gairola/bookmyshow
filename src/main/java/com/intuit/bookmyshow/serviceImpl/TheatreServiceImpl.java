package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.entity.City;
import com.intuit.bookmyshow.entity.Screen;
import com.intuit.bookmyshow.entity.Theatre;
import com.intuit.bookmyshow.repository.CityRepository;
import com.intuit.bookmyshow.repository.TheatreRepository;
import com.intuit.bookmyshow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private CityRepository cityRepository;

    public Theatre createTheatre(UUID cityId, Theatre theatre) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            theatre.setCity(city);
            return theatreRepository.save(theatre);
        } else {
            return null; // Or handle as per your requirements
        }
    }

    public Theatre getTheatre(UUID id){
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()){
            return theatre.get();
        }else{
            return null;
        }
    }
    public Theatre updateTheatre(UUID id, Theatre updatedTheatre) {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(id);
        if (optionalTheatre.isPresent()) {
            Theatre theatre = optionalTheatre.get();
            // Update the theatre fields
            theatre.setCity(updatedTheatre.getCity());
            theatre.setAddress(updatedTheatre.getAddress());
            theatre.setLatitude(updatedTheatre.getLatitude());
            theatre.setLongitude(updatedTheatre.getLongitude());
            theatre.setScreenList(updatedTheatre.getScreenList());
            return theatreRepository.save(theatre);
        } else {
            return null;
        }
    }
    public Theatre addScreenToTheatre(UUID id, Screen screen) {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(id);
        if (optionalTheatre.isPresent()) {
            Theatre theatre = optionalTheatre.get();
            theatre.getScreenList().add(screen);
            return theatreRepository.save(theatre);
        } else {
            return null;
        }
    }
}
