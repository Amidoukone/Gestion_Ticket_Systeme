package com.master.gestion_ticket.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Timestamp date;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Ticket ticket;

    // Getters
    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getDate() {
        return date;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Ticket getTicket() {
        return ticket;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

