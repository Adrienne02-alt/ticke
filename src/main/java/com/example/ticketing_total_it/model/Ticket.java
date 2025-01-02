package com.example.ticketing_total_it.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", length = 200, nullable = false)
    private String titre;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "categorie", length = 100)
    private String categorie;

    @Column(name = "pourcentage", length = 100)
    private Integer pourcentage;

    @Enumerated(EnumType.STRING)
    @Column(name = "priorite", length = 50, nullable = false)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 50)
    private Statut statut = Statut.ouvert;

    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @CreationTimestamp
    @Column(name = "date_mise_a_jour")
    private LocalDateTime dateMiseAJour;

    @CreationTimestamp
    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "employe_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_ticket_employe"))
    private Utilisateur employe;

    @ManyToOne
    @JoinColumn(name = "technicien_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_ticket_technicien"))
    private Utilisateur technicien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(LocalDateTime dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public Utilisateur getEmploye() {
        return employe;
    }

    public void setEmploye(Utilisateur employe) {
        this.employe = employe;
    }

    public Utilisateur getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Utilisateur technicien) {
        this.technicien = technicien;
    }

    public Integer getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Integer pourcentage) {
        this.pourcentage = pourcentage;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public enum Priorite {
        basse, moyenne, haute
    }

    public enum Statut {
        ouvert, en_cours, ferme, reouvert
    }
}
