package com.booking.irctc.dto;
import lombok.Data;

@Data
public class TicketDTO {
    Long trainId;
    String seatNumber;
    String journeyDate;
}
