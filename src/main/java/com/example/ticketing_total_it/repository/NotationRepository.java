package com.example.ticketing_total_it.repository;

import com.example.ticketing_total_it.model.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotationRepository extends JpaRepository<Notation, Long> {

    List<Notation> findByTicketId(Long ticketId);
    List<Notation> findByTechnicienId(Long technicienId);
}
