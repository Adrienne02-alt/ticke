package com.example.ticketing_total_it.repository;

import com.example.ticketing_total_it.model.Reouverture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReouvertureRepository extends JpaRepository<Reouverture, Long> {
}
