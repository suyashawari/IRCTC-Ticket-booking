package com.booking.irctc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private String SeatNumber;
    private String bookingTime;
    private String journeyDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId" ,nullable = false)
    @JsonIgnore
    private UserData userdata;
    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "trainId",nullable = false)
    private Train train;

}
