package com.intuit.bookmyshow.service;

import com.intuit.bookmyshow.entity.Screen;
import com.intuit.bookmyshow.entity.Theatre;

import java.util.UUID;

public interface TheatreService {
    Theatre createTheatre(UUID cityId, Theatre theatre);
    Theatre getTheatre(UUID id);
    Theatre updateTheatre(UUID id, Theatre updatedTheatre);
    Theatre addScreenToTheatre(UUID id, Screen screen);

}
