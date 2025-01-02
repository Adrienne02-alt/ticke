package com.example.ticketing_total_it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketing_total_it.model.Categorie;
import com.example.ticketing_total_it.repository.CategorieRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://css-two-black.vercel.app", allowCredentials = "true")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;


    @PostMapping("/save_categorie")
    public ResponseEntity<Categorie> save_categorie(@RequestBody Categorie cate){
        Categorie categorie = categorieRepository.save(cate);
        return ResponseEntity.ok(categorie);
    }

    @GetMapping("/allCategorie")
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<Categorie> getCategorieByName(@RequestParam Long id) {
        return categorieRepository.findById(id)
            .map(categorie -> ResponseEntity.ok(categorie))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/update_categorie/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie updatedCategorie) {
        return categorieRepository.findById(id)
            .map(categorie -> {
                categorie.setNom(updatedCategorie.getNom());
                Categorie savediCategorie = categorieRepository.save(categorie);
                return ResponseEntity.ok(savediCategorie);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete_categorie/{id}")
    public ResponseEntity<Void> deletecategorie(@PathVariable Long id) {
        return categorieRepository.findById(id)
            .map(categorie -> {
                categorieRepository.delete(categorie);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
