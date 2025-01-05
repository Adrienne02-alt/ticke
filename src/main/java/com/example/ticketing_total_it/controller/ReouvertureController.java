package com.example.ticketing_total_it.controller;

import com.example.ticketing_total_it.model.Reouverture;
import com.example.ticketing_total_it.repository.ReouvertureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://css-28b4.vercel.app/", allowCredentials = "true")
public class ReouvertureController {

    @Autowired
    private ReouvertureRepository reouvertureRepository;

    // Endpoint pour créer une nouvelle réouverture
    @PostMapping("/save_reouverture")
    public ResponseEntity<Reouverture> saveReouverture(@RequestBody Reouverture reouverture) {
        Reouverture savedReouverture = reouvertureRepository.save(reouverture);
        return ResponseEntity.ok(savedReouverture);
    }

    // Endpoint pour récupérer toutes les réouvertures
    @GetMapping("/allReouvertures")
    public List<Reouverture> getAllReouvertures() {
        return reouvertureRepository.findAll();
    }

    // Endpoint pour récupérer une réouverture par son ID
    @GetMapping("/reouverture/{id}")
    public ResponseEntity<Reouverture> getReouvertureById(@PathVariable Long id) {
        return reouvertureRepository.findById(id)
            .map(reouverture -> ResponseEntity.ok(reouverture))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer une réouverture
    @DeleteMapping("/delete_reouverture/{id}")
    public ResponseEntity<Void> deleteReouverture(@PathVariable Long id) {
        return reouvertureRepository.findById(id)
            .map(reouverture -> {
                reouvertureRepository.delete(reouverture);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
