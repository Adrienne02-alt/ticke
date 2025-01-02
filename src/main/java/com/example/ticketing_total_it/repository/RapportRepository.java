package com.example.ticketing_total_it.repository;

import com.example.ticketing_total_it.model.Rapport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
    @Query("SELECT r FROM Rapport r WHERE r.createur.id = :createurId")
    List<Rapport> findByCreateurId(@Param("createurId") Long createurId);
}
