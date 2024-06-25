package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.Utilisateur;
import com.master.gestion_ticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManageAccountService {
    private UtilisateurRepository utilisateurRepository;


    public Utilisateur loadByUsername(String email){
        return utilisateurRepository.findByEmail1(email);
    }
}
