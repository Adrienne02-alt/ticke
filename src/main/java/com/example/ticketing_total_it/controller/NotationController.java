package com.example.ticketing_total_it.controller;

import com.example.ticketing_total_it.model.Notation;
import com.example.ticketing_total_it.repository.NotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notations")
@CrossOrigin(origins = "https://css-28b4.vercel.app", allowCredentials = "true")
public class NotationController {

    @Autowired
    private NotationRepository notationRepository;

    // Créer une notation
    @PostMapping("/create")
    public ResponseEntity<Notation> createNotation(@RequestBody Notation notation) {
        Notation createdNotation = notationRepository.save(notation);
        return ResponseEntity.ok(createdNotation);
    }

    // Récupérer toutes les notations
    @GetMapping("/all")
    public List<Notation> getAllNotations() {
        return notationRepository.findAll();
    }

    // Récupérer les notations par ticket
    @GetMapping("/byTicket/{ticketId}")
    public List<Notation> getNotationsByTicket(@PathVariable Long ticketId) {
        return notationRepository.findByTicketId(ticketId);
    }

    // Récupérer les notations par technicien
    @GetMapping("/byTechnicien/{technicienId}")
    public List<Notation> getNotationsByTechnicien(@PathVariable Long technicienId) {
        return notationRepository.findByTechnicienId(technicienId);
    }

    // Mettre à jour une notation
    @PutMapping("/update/{id}")
    public ResponseEntity<Notation> updateNotation(@PathVariable Long id, @RequestBody Notation updatedNotation) {
        return notationRepository.findById(id)
            .map(notation -> {
                notation.setNote(updatedNotation.getNote());
                notation.setCommentaire(updatedNotation.getCommentaire());
                Notation savedNotation = notationRepository.save(notation);
                return ResponseEntity.ok(savedNotation);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer une notation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNotation(@PathVariable Long id) {
        return notationRepository.findById(id)
            .map(notation -> {
                notationRepository.delete(notation);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
