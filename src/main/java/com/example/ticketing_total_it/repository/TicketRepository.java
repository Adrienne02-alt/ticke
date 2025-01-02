package com.example.ticketing_total_it.repository;

import com.example.ticketing_total_it.model.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatut(String statut);
    List<Ticket> findByStatutIn(List<String> statuts);
    List<Ticket> findByTechnicienId(Long technicienId);
    List<Ticket> findByEmployeId(Long employeId);
    List<Ticket> findByTechnicienIdAndStatutIn(Long technicienId, List<String> statuts);
}
