package com.booking.irctc.repository;

import com.booking.irctc.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query(value = "SELECT * from tickets where user_id = :userId",
    nativeQuery = true)
    List<Ticket> getTicketByUserdata_UserId(@Param("userId") Integer userId);
}
