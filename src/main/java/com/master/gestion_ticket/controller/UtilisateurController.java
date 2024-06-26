package com.master.gestion_ticket.controller;

import com.master.gestion_ticket.entity.Utilisateur;
import com.master.gestion_ticket.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@AllArgsConstructor
public class UtilisateurController {
    //@Autowired
    private UtilisateurService utilisateurService;

    @PreAuthorize(("hasRole('ADMIN')"))
    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        //Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        //return ResponseEntity.ok(createdUtilisateur);
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }
    @GetMapping("/list2")
    public List<Utilisateur> getAllUtilisateurs1() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return utilisateurs;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
