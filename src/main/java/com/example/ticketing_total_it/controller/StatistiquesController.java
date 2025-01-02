package com.example.ticketing_total_it.controller;

import com.example.ticketing_total_it.model.Notation;
import com.example.ticketing_total_it.model.Ticket;
import com.example.ticketing_total_it.repository.NotationRepository;
import com.example.ticketing_total_it.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://css-two-black.vercel.app", allowCredentials = "true")
public class StatistiquesController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NotationRepository notationRepository;

    // 1. Nombre de tickets traités et résolus par un technicien
    @GetMapping("/tickets/traite-resolu")
    public long getTicketsTraitesEtResolus(@RequestParam Long technicienId) {
        // Récupère les tickets traités et résolus par un technicien
        List<Ticket> tickets = ticketRepository.findByTechnicienIdAndStatutIn(technicienId, List.of("ferme", "reouvert"));
        return tickets.size();
    }

    // 2. Délai moyen de résolution des tickets pour un technicien
    @GetMapping("/tickets/delai-moyen-resolution")
    public double getDélaiMoyenResolution(@RequestParam Long technicienId) {
        List<Ticket> tickets = ticketRepository.findByTechnicienIdAndStatutIn(technicienId, List.of("ferme", "reouvert"));
        OptionalDouble averageDuration = tickets.stream()
                .filter(ticket -> ticket.getDateCreation() != null && ticket.getDateMiseAJour() != null)
                .mapToLong(ticket -> Duration.between(ticket.getDateCreation(), ticket.getDateMiseAJour()).toDays())
                .average();

        return averageDuration.orElse(0.0);
    }

    @GetMapping("/tickets/evaluations")
    public double getEvaluations(@RequestParam Long technicienId) {
        List<Notation> notations = notationRepository.findByTechnicienId(technicienId);

        // Calcul de la note moyenne
        OptionalDouble averageRating = notations.stream()
                .mapToInt(Notation::getNote)
                .average();

        return averageRating.orElse(0.0);
    }
}
