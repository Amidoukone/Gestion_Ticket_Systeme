package com.master.gestion_ticket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;


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
    private Date dateCreation;
    private Timestamp dateResolution;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Utilisateur apprenant;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Utilisateur formateur;

    //@OneToMany(mappedBy = "ticket")
   // private Set<Reponse> reponses;

}
