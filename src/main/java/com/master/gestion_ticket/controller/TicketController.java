package com.master.gestion_ticket.controller;

import com.master.gestion_ticket.entity.Ticket;
import com.master.gestion_ticket.entity.Utilisateur;
import com.master.gestion_ticket.repository.UtilisateurRepository;
import com.master.gestion_ticket.service.EmailService;
import com.master.gestion_ticket.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final UtilisateurRepository utilisateurRepository;
    private final EmailService emailService;

    @PreAuthorize("hasRole('APPRENANT')")
    @PostMapping("/t")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);

        // Récupérer le formateur associé au ticket
        //if (formateur!= null && "FORMATEUR".equals(formateur.getRole())) { // Vérifier le rôle du formateur
            // Construire le sujet et le contenu du message
        List<Utilisateur> formateurs = utilisateurRepository.findByRole("FORMATEUR");
        for (Utilisateur formateur : formateurs){
            String emailFormateur = formateur.getEmail();
            String subject = "Nouveau Ticket Créé";
            String body = "Un nouveau ticket a été créé par " + ticket.getApprenant().getNom();

            // Envoyer l'email au formateur
            emailService.sendSimpleMessage(emailFormateur, subject, body);
        }
        //}

        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
