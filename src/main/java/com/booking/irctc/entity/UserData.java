package com.booking.irctc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_data")
public class UserData {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;
    private String password;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "userdata", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
