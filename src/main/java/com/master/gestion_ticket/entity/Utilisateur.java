package com.master.gestion_ticket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;

    //@OneToMany(mappedBy = "apprenant")
    //private List<Ticket> tickets;

    //@OneToMany(mappedBy = "formateur")
    //private List<Reponse> reponses;

    // Getters and Setters
}
