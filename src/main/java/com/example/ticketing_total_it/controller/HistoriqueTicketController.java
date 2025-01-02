package com.example.ticketing_total_it.controller;

import com.example.ticketing_total_it.model.HistoriqueTicket;
import com.example.ticketing_total_it.repository.HistoriqueTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historique_tickets")
@CrossOrigin(origins = "http://localhost:8085", allowCredentials = "true")
public class HistoriqueTicketController {

    @Autowired
    private HistoriqueTicketRepository historiqueTicketRepository;

    // Créer un historique d'action pour un ticket
    @PostMapping("/create")
    public ResponseEntity<HistoriqueTicket> createHistorique(@RequestBody HistoriqueTicket historiqueTicket) {
        HistoriqueTicket createdHistorique = historiqueTicketRepository.save(historiqueTicket);
        return ResponseEntity.ok(createdHistorique);
    }

    // Récupérer l'historique complet des tickets
    @GetMapping("/all")
    public List<HistoriqueTicket> getAllHistorique() {
        return historiqueTicketRepository.findAll();
    }

    // Récupérer l'historique d'un ticket spécifique
    @GetMapping("/byTicket/{ticketId}")
    public List<HistoriqueTicket> getHistoriqueByTicket(@PathVariable Long ticketId) {
        return historiqueTicketRepository.findByTicketId(ticketId);
    }

    // Récupérer l'historique d'un utilisateur spécifique
    @GetMapping("/byUtilisateur/{utilisateurId}")
    public List<HistoriqueTicket> getHistoriqueByUtilisateur(@PathVariable Long utilisateurId) {
        return historiqueTicketRepository.findByUtilisateurId(utilisateurId);
    }

    // Mettre à jour un historique (si nécessaire, dans un cas d'action supplémentaire)
    @PutMapping("/update/{id}")
    public ResponseEntity<HistoriqueTicket> updateHistorique(@PathVariable Long id, @RequestBody HistoriqueTicket updatedHistorique) {
        return historiqueTicketRepository.findById(id)
            .map(historique -> {
                historique.setAction(updatedHistorique.getAction());
                historique.setUtilisateur(updatedHistorique.getUtilisateur());
                historique.setDateAction(updatedHistorique.getDateAction());
                HistoriqueTicket savedHistorique = historiqueTicketRepository.save(historique);
                return ResponseEntity.ok(savedHistorique);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un historique d'action
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHistorique(@PathVariable Long id) {
        return historiqueTicketRepository.findById(id)
            .map(historique -> {
                historiqueTicketRepository.delete(historique);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
