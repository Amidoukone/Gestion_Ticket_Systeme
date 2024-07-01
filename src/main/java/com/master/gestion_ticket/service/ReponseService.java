package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.*;
import com.master.gestion_ticket.repository.ReponseRepository;
import com.master.gestion_ticket.repository.TicketRepository;
import com.master.gestion_ticket.repository.BaseDeConnaissancesRepository;
import com.master.gestion_ticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReponseService {

    private final ReponseRepository reponseRepository;
    private final TicketRepository ticketRepository;
    private final BaseDeConnaissancesRepository baseDeConnaissancesRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final EmailService emailService;

    public Reponse createReponse(Long ticketId, Reponse reponse) {
        // Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Utilisateur currentFormateur = utilisateurRepository.findByEmail1(email);

        // Vérifier que le formateur actuel est trouvé
        if (currentFormateur == null) {
            throw new RuntimeException("Formateur non trouvé");
        }

        // Récupérer le ticket et l'apprenant associé
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
        Utilisateur apprenant = ticket.getApprenant();

        // Envoyer l'email à l'apprenant
        String emailApprenant = apprenant.getEmail();
        String subject = "Réponse au Ticket";
        String body = "Salut, votre ticket a été répondu par notre Formateur " + currentFormateur.getNom();
        emailService.sendSimpleMessage(emailApprenant, subject, body);

        // Mettre à jour la réponse
        reponse.setTicket(ticket);
        reponse.setDateReponse(new Timestamp(System.currentTimeMillis()));
        reponse.setFormateur(currentFormateur);

        return reponseRepository.save(reponse);
    }

    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    public Reponse updateReponse(Long id, Reponse reponse) {
        reponse.setId(id);
        return reponseRepository.save(reponse);
    }

    public void deleteReponse(Long id) {
        reponseRepository.deleteById(id);
    }
}
