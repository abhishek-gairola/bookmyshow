package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.entity.Screen;
import com.intuit.bookmyshow.entity.Seat;
import com.intuit.bookmyshow.entity.Theatre;
import com.intuit.bookmyshow.repository.ScreenRepository;
import com.intuit.bookmyshow.repository.SeatRepository;
import com.intuit.bookmyshow.repository.TheatreRepository;
import com.intuit.bookmyshow.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private SeatRepository seatRepository;

    public Screen createScreen(UUID theatreId, List<String> seatNames) {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
        if (optionalTheatre.isPresent()) {
            Theatre theatre = optionalTheatre.get();

            // Create a new screen and associate it with the theatre
            Screen screen = new Screen();
            screen.setTheatre(theatre);
            screen = screenRepository.save(screen);

            // Create seats and associate them with the newly created screen
            List<Seat> seats = new ArrayList<>();
            for (String seatName : seatNames) {
                Seat seat = new Seat();
                seat.setSeatNumber(seatName);
                seat.setScreenId(screen.getId());
                seats.add(seat);
            }
            Iterable<Seat> savedSeats = seatRepository.saveAll(seats);
            List<Seat> savedSeatList = new ArrayList<>();
            savedSeats.forEach(savedSeatList::add);
            // Set the list of seats to the screen
            screen.setSeats(savedSeatList);

            return screen;
        } else {
            return null; // Or handle as per your requirements
        }
    }

    public Screen addSeatsToScreen(UUID screenId, List<String> seatNames) {
        Optional<Screen> optionalScreen = screenRepository.findById(screenId);
        if (optionalScreen.isPresent()) {
            Screen screen = optionalScreen.get();
            List<Seat> existingSeats = screen.getSeats();
            for (String seatName : seatNames) {
                if (existingSeats.stream().noneMatch(seat -> seat.getSeatNumber().equals(seatName))) {
                    Seat newSeat = new Seat();
                    newSeat.setSeatNumber(seatName);
                    newSeat.setScreenId(screen.getId());
                    existingSeats.add(newSeat);
                }
            }
            screen.setSeats(existingSeats);
            return screenRepository.save(screen);
        } else {
            return null;
        }
    }

    public Screen updateScreen(UUID screenId, Screen updatedScreen) {
        Optional<Screen> optionalScreen = screenRepository.findById(screenId);
        if (optionalScreen.isPresent()) {
            Screen screen = optionalScreen.get();
            screen.setTheatre(updatedScreen.getTheatre());
            screen.setSeats(updatedScreen.getSeats());
            return screenRepository.save(screen);
        } else {
            return null;
        }
    }
}
