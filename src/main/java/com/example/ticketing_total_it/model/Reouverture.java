package com.example.ticketing_total_it.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reouvertures")
public class Reouverture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "tickets", nullable = false, foreignKey = @ForeignKey(name = "fk_reouvertures_tickets"))
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "utilisateurs", nullable = false, foreignKey = @ForeignKey(name = "fk_reouvertures_utilisateurs"))
    private Utilisateur utilisateur;

    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    @CreationTimestamp
    @Column(name = "date_reouverture", updatable = false)
    private LocalDateTime dateReouverture;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getDateReouverture() {
        return dateReouverture;
    }

    public void setDateReouverture(LocalDateTime dateReouverture) {
        this.dateReouverture = dateReouverture;
    }
}
