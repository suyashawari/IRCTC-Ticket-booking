package com.booking.irctc.controller;

import com.booking.irctc.dto.TicketDTO;
import com.booking.irctc.entity.Ticket;
import com.booking.irctc.services.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketBookingService ticketservice;
    
    @GetMapping("/list")
    public ResponseEntity<List<Ticket>> listTicket (){
        List<Ticket> userTickets=ticketservice.getTicketList();
        if (userTickets.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(userTickets, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createTicket (@RequestBody TicketDTO ticket){
       try {
           if (ticket!=null){

               ticketservice.createTicket(ticket);
               return new ResponseEntity<>(true, HttpStatus.CREATED);
           }else return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       
    }
}
