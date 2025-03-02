package com.booking.irctc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Train {
    @Id
    @GeneratedValue(strategy= jakarta.persistence.GenerationType.IDENTITY)
    private Integer trainId;

    private String trainName;
    private String fromStation;
    private String toStation;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ticket> tickets;
}
