package com.master.gestion_ticket.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

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

    @ManyToOne
    private Utilisateur formateur;

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getPriorite() {
        return priorite;
    }

    public String getEtat() {
        return etat;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public Timestamp getDateResolution() {
        return dateResolution;
    }

    public Utilisateur getApprenant() {
        return apprenant;
    }

    public Utilisateur getFormateur() {
        return formateur;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateResolution(Timestamp dateResolution) {
        this.dateResolution = dateResolution;
    }

    public void setApprenant(Utilisateur apprenant) {
        this.apprenant = apprenant;
    }

    public void setFormateur(Utilisateur formateur) {
        this.formateur = formateur;
    }
}
