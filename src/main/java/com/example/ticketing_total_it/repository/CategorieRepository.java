package com.example.ticketing_total_it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketing_total_it.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    
}
