package com.example.ticketing_total_it.controller;

import com.example.ticketing_total_it.model.Ticket;
import com.example.ticketing_total_it.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "https://css-two-black.vercel.app", allowCredentials = "true")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    // Créer un ticket
    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(createdTicket);
    }

    // Récupérer tous les tickets
    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Récupérer un ticket par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id)
            .map(ticket -> ResponseEntity.ok(ticket))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un ticket par son ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        return ticketRepository.findById(id)
            .map(ticket -> {
                ticket.setTitre(updatedTicket.getTitre());
                ticket.setDescription(updatedTicket.getDescription());
                ticket.setCategorie(updatedTicket.getCategorie());
                ticket.setPriorite(updatedTicket.getPriorite());
                ticket.setStatut(updatedTicket.getStatut());
                ticket.setEmploye(updatedTicket.getEmploye());
                ticket.setTechnicien(updatedTicket.getTechnicien());
                ticket.setDateMiseAJour(updatedTicket.getDateMiseAJour());
                Ticket savedTicket = ticketRepository.save(ticket);
                return ResponseEntity.ok(savedTicket);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tickets/ouverts")
    public List<Ticket> getTicketsOuverts() {
        return ticketRepository.findByStatut("ouvert");
    }

    @GetMapping("/tickets/technicien")
    public List<Ticket> getTicketsParTechnicien(@RequestParam Long technicienId) {
        return ticketRepository.findByTechnicienId(technicienId);
    }

    @GetMapping("/tickets/employe")
    public List<Ticket> getTicketsParEmploye(@RequestParam Long employeId) {
        return ticketRepository.findByEmployeId(employeId);
    }

    // Supprimer un ticket par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        return ticketRepository.findById(id)
            .map(ticket -> {
                ticketRepository.delete(ticket);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
