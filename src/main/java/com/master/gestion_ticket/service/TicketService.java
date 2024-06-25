// TicketService.java
package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.Notification;
import com.master.gestion_ticket.entity.Ticket;
import com.master.gestion_ticket.entity.Utilisateur;
import com.master.gestion_ticket.repository.TicketRepository;
import com.master.gestion_ticket.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Ticket createTicket(Ticket ticket) {
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

    public Ticket respondToTicket(Long id, String response, Long formateurId) {
        // Logic to respond to a ticket and create a notification
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setEtat("Répondu");
        ticketRepository.save(ticket);

        Notification notification = new Notification();
        notification.setMessage("Un ticket a été répondu par " + ticket.getFormateur().getNom());
        notification.setDateNotification(new Timestamp(System.currentTimeMillis()));
        notification.setTicket(ticket);
        notification.setUtilisateur(ticket.getFormateur());
        notificationService.createNotification(notification);

        return ticket;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
}
