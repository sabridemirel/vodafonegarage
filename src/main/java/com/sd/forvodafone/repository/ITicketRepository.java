package com.sd.forvodafone.repository;

import com.sd.forvodafone.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findByStatus(int status);
}
