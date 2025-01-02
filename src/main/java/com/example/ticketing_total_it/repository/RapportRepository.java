package com.example.ticketing_total_it.repository;

import com.example.ticketing_total_it.model.Rapport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
    List<Rapport> findByCreateur(Long createurId);
}
