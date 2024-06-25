package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.Utilisateur;

import com.master.gestion_ticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private ManageAccountService manageAccountService;




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = manageAccountService.loadByUsername(email);
        if (utilisateur == null) throw new UsernameNotFoundException("Utilisateur non trouvé");
        System.out.println(utilisateur.getEmail());
        System.out.println(utilisateur.getPassword());
        System.out.println(utilisateur.getRole());
        return User.withUsername(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .roles(utilisateur.getRole())
                .build();
    }

}
