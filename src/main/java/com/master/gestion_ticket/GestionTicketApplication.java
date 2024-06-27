package com.master.gestion_ticket;

import com.master.gestion_ticket.entity.Utilisateur;
import com.master.gestion_ticket.service.EmailService;
import com.master.gestion_ticket.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.Scanner;

@SpringBootApplication
public class GestionTicketApplication implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UtilisateurService utilisateurService;

    public static void main(String[] args) {
        SpringApplication.run(GestionTicketApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Test sending an email
        //emailService.sendSimpleMessage("koneamidou27@gmail.com", "Test Subject", "Test Email Body");z
        Scanner entre = new Scanner(System.in);
        System.out.println("Donner le nom de m'admin");
        String nom = entre.nextLine();
        System.out.println("Donner le email de l'admin");
        String email = entre.nextLine();
        System.out.println("Donner le mot de passe");
        String mdp = entre.nextLine();

        Utilisateur admin = new Utilisateur();

        admin.setNom(nom);
        admin.setEmail(email);
        admin.setMotDePasse(mdp);
        admin.setRole("ADMIN");

        utilisateurService.createUtilisateur(admin);


    }
}
