package com.example.ticketing_total_it.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketing_total_it.DTO_Authentification.LoginRequest;
import com.example.ticketing_total_it.DTO_Authentification.PasswordRequest;
import com.example.ticketing_total_it.model.Utilisateur;
import com.example.ticketing_total_it.model.Utilisateur.Role;
import com.example.ticketing_total_it.repository.UtilisateurRepository;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8085", allowCredentials = "true")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository userRepository;
    
    @GetMapping("/home")
    public String getMethodName(@RequestParam String param) {
        return new String("Hello world");
    }

    @GetMapping("/allUsers")
    public List<Utilisateur> getAllUtilisateur() {
        return userRepository.findAll();
    }

    @PostMapping("/insertion_user")
    public ResponseEntity<Utilisateur> inscription(@RequestBody  Utilisateur user){
        Utilisateur savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session){
        Utilisateur user = userRepository.findByEmail(loginRequest.getEmail())
            .orElse(null);

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }

        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole());

        return ResponseEntity.ok(user);
    }

    @PutMapping("/update_password/{email}")
    public ResponseEntity<?> updatePassword(@PathVariable String email, @RequestBody PasswordRequest password) {
        return userRepository.findByEmail(email)
            .map(user -> {
                user.setPassword(password.getPassword());
                Utilisateur updatedUser = userRepository.save(user);
                return ResponseEntity.ok("Le mot de passe a été mis à jour avec succès pour " + updatedUser.getEmail());
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé"));
    }

    @GetMapping("/session-info")
    public ResponseEntity<?> getSessionInfo(HttpSession session) {
        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("email", session.getAttribute("email"));
        sessionData.put("role", session.getAttribute("role"));
        return ResponseEntity.ok(sessionData);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        if (session != null) {
            session.invalidate();  // Supprime toutes les informations de session
        }
        return ResponseEntity.ok("Déconnexion réussie.");
    }

    @PutMapping("/role_assignation/{email}")
    public ResponseEntity<?> updateRole(@PathVariable String email, @RequestBody Role newRole) {
        return userRepository.findByEmail(email)
            .map(user -> {
                user.setRole(newRole);  // Mise à jour du rôle de l'utilisateur
                Utilisateur updatedUser = userRepository.save(user);
                return ResponseEntity.ok("Le rôle a été mis à jour avec succès pour " + updatedUser.getEmail());
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé"));
    }

}
