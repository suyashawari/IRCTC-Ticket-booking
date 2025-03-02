package com.booking.irctc.services;

import com.booking.irctc.dto.TicketDTO;
import com.booking.irctc.entity.Ticket;
import com.booking.irctc.entity.Train;
import com.booking.irctc.entity.UserData;
import com.booking.irctc.repository.TicketRepository;
import com.booking.irctc.repository.TrainRepository;
import com.booking.irctc.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketBookingService {
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private TrainRepository trainRepo;

    public List<Train> getTrainList() {
        return trainRepo.findAll();
    }

    public Boolean createTicket(TicketDTO ticketdto
    ) throws Exception {
        Train train = trainRepo.findById(ticketdto.getTrainId()).orElseThrow(() -> new Exception("train not found with id " + ticketdto.getTrainId()));
        Ticket ticket = new Ticket();
        if (train.getAvailableSeats() <= 0) {
            
            throw new RuntimeException("No seats availabe on this train!..");

        }
        if (userService.getLoggedInUser() == null) {
            throw new RuntimeException("No user logedin yet!..");

        } else ticket.setUserdata(userService.getLoggedInUser());
//       check the all data of ticketdto is not null
        if (ticketdto.getSeatNumber() == null || ticketdto.getSeatNumber().isEmpty()) {
            throw new IllegalArgumentException("SeatNumber cannot be null or empty!");
        }
        ticket.setSeatNumber(ticketdto.getSeatNumber());
        ticket.setBookingTime(LocalDateTime.now().toString());
        ticket.setJourneyDate(ticketdto.getJourneyDate());
        ticket.setTrain(train);
        ticket.setStatus("BOOKED");
        ticketRepo.save(ticket);
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepo.save(train);

        return true;
    }

    public List<Ticket> getTicketList() {
        UserData user = userService.getLoggedInUser();
        return ticketRepo.getTicketByUserdata_UserId(user.getUserId());
    }
}
