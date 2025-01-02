package com.example.ticketing_total_it.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom", length=100, nullable = false)
    private String nom;

    @Column(name="email", length=150, nullable = false, unique = true)
    private String email;

    @Column(name="mot_de_passe", length=255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role", length=50, nullable = false)
    private Role role;

    @CreationTimestamp
    @Column(name="date_creation", updatable = false)
    private LocalDateTime dateCreation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String motDePasse) {
        this.password = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public enum Role {
        employe, technicien, administrateur;
    }
}
