// TicketService.java
package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.Notification;
import com.master.gestion_ticket.entity.Ticket;
import com.master.gestion_ticket.entity.Utilisateur;
import com.master.gestion_ticket.repository.TicketRepository;
import com.master.gestion_ticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service

public class TicketService {
    //@Autowired
    private TicketRepository ticketRepository;

    //@Autowired
    private NotificationService notificationService;

    //@Autowired
    private UtilisateurRepository utilisateurRepository;

    private PasswordEncoder passwordEncoder;


    public Ticket createTicket(Ticket ticket) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Utilisateur apprenant = utilisateurRepository.findByEmail1(email);
        ticket.setApprenant(apprenant);
        // Assuming ticket.formateur is just the ID or not fully loaded
        // Utilisateur formateur = utilisateurRepository.findById(ticket.getFormateur().getId()).orElse(null);
      //  if (formateur != null) {
        //    ticket.setFormateur(formateur);
       // }



        ticket.setDateCreation(new Date());
        Ticket createdTicket = ticketRepository.save(ticket);

        // Create a notification for the formateur when a new ticket is created
       // if (formateur != null) {
            Notification notification = new Notification();
            notification.setMessage("Un nouveau ticket a été créé par " + ticket.getApprenant().getNom());
            notification.setDateNotification(new Timestamp(System.currentTimeMillis()));
            notification.setTicket(createdTicket);
            notification.setUtilisateur(ticket.getFormateur());
            notificationService.createNotification(notification);
       // }

        return createdTicket;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }


    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
}
