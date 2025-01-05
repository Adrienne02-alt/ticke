package com.example.ticketing_total_it.repository;

import com.example.ticketing_total_it.model.HistoriqueTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoriqueTicketRepository extends JpaRepository<HistoriqueTicket, Long> {

    // Trouver les historiques d'un utilisateur
    List<HistoriqueTicket> findByUtilisateurId(Long utilisateurId);
}
