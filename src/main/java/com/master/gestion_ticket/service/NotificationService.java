package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.Notification;
import com.master.gestion_ticket.entity.Ticket;
import com.master.gestion_ticket.entity.Utilisateur;
import com.master.gestion_ticket.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification updateNotification(Long id, Notification notification) {
        notification.setId(id);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public Notification createNotificationForTicketResponse(Ticket ticket) {
        Utilisateur formateur = ticket.getFormateur();
        if (formateur != null) {
            Notification notification = new Notification();
            notification.setMessage("Un ticket a été répondu par " + formateur.getNom());
            notification.setDateNotification(new Timestamp(System.currentTimeMillis()));
            notification.setTicket(ticket);  // Set ticket reference
            notification.setUtilisateur(formateur); // Set utilisateur reference
            return notificationRepository.save(notification);
        }
        return null;
    }
}
