package com.master.gestion_ticket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime; // Importez LocalDateTime au lieu de Timestamp

@Entity
public class BaseDeConnaissances {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String reponse;
    private String categorie;
    private LocalDateTime date; // Modifiez Timestamp par LocalDateTime pour être conforme à Java

    @ManyToOne
    private Utilisateur formateur;

    // Getters
    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponse() {
        return reponse;
    }

    public String getCategorie() {
        return categorie;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Utilisateur getFormateur() {
        return formateur;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFormateur(Utilisateur formateur) {
        this.formateur = formateur;
    }
}
