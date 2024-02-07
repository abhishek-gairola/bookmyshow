package com.intuit.bookmyshow.service;

import com.intuit.bookmyshow.entity.Screen;

import java.util.List;
import java.util.UUID;

public interface ScreenService {
    Screen createScreen(UUID theatreId, List<String> seatNames);
    Screen addSeatsToScreen(UUID screenId, List<String> seatNames);
    Screen updateScreen(UUID screenId, Screen updatedScreen);
}
