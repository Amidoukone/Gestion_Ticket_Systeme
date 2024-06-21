package com.master.gestion_ticket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String categorie;
    private String priorite;
    private String etat;
    private Timestamp dateCreation;
    private Timestamp dateResolution;

    @ManyToOne
    private Utilisateur apprenant;

    public Utilisateur getFormateur() {
        return null;
    }

    //@OneToMany(mappedBy = "ticket")
    //private List<Reponse> reponses;

    // Getters and Setters
}
