package com.master.gestion_ticket.controller;

import com.master.gestion_ticket.entity.Reponse;
import com.master.gestion_ticket.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reponses")
public class ReponseController {
    @Autowired
    private ReponseService reponseService;

    @PostMapping("/{ticketId}")
    public ResponseEntity<Reponse> createReponse(@PathVariable Long ticketId, @RequestBody Reponse reponse) {
        Reponse createdReponse = reponseService.createReponse(ticketId, reponse);
        return ResponseEntity.ok(createdReponse);
    }

    @GetMapping
    public ResponseEntity<List<Reponse>> getAllReponses() {
        List<Reponse> reponses = reponseService.getAllReponses();
        return ResponseEntity.ok(reponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable Long id, @RequestBody Reponse reponse) {
        Reponse updatedReponse = reponseService.updateReponse(id, reponse);
        return ResponseEntity.ok(updatedReponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long id) {
        reponseService.deleteReponse(id);
        return ResponseEntity.noContent().build();
    }
}
