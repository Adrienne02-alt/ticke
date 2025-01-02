package com.example.ticketing_total_it.controller;

import com.example.ticketing_total_it.model.Rapport;
import com.example.ticketing_total_it.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://css-two-black.vercel.app", allowCredentials = "true")
public class RapportController {

    @Autowired
    private RapportRepository rapportRepository;

    // Créer un rapport
    @PostMapping("/save_rapport")
    public ResponseEntity<Rapport> createRapport(@RequestBody Rapport rapport) {
        Rapport savedRapport = rapportRepository.save(rapport);
        return ResponseEntity.ok(savedRapport);
    }

    // Récupérer tous les rapports
    @GetMapping("/allRapports")
    public List<Rapport> getAllRapports() {
        return rapportRepository.findAll();
    }

    // Récupérer un rapport par son ID
    @GetMapping("/rapport/{id}")
    public ResponseEntity<Rapport> getRapportById(@PathVariable Long id) {
        Optional<Rapport> rapport = rapportRepository.findById(id);
        return rapport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rapports/technicien")
    public List<Rapport> getRapportsParTechnicien(@RequestParam Long technicienId) {
        // Récupère les rapports créés par le technicien
        return rapportRepository.findByCreateurId(technicienId);
    }

    // Mettre à jour un rapport
    @PutMapping("/update_rapport/{id}")
    public ResponseEntity<Rapport> updateRapport(@PathVariable Long id, @RequestBody Rapport updatedRapport) {
        return rapportRepository.findById(id)
            .map(rapport -> {
                rapport.setTitre(updatedRapport.getTitre());
                rapport.setContenu(updatedRapport.getContenu());
                rapport.setTicket(updatedRapport.getTicket());
                rapport.setCreateur(updatedRapport.getCreateur());
                Rapport savedRapport = rapportRepository.save(rapport);
                return ResponseEntity.ok(savedRapport);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un rapport
    @DeleteMapping("/delete_rapport/{id}")
    public ResponseEntity<Void> deleteRapport(@PathVariable Long id) {
        return rapportRepository.findById(id)
            .map(rapport -> {
                rapportRepository.delete(rapport);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
