package com.master.gestion_ticket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenu;
    private Timestamp dateReponse;

    @ManyToOne
    private Utilisateur formateur;

    @ManyToOne
    private Ticket ticket;

    // Getters and Setters
}
