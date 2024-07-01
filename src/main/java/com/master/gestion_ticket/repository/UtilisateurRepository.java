package com.master.gestion_ticket.repository;

import com.master.gestion_ticket.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    List<Utilisateur> findByRole(String role);
    @Query("select u from Utilisateur u where u.email=:x")
    Utilisateur findByEmail1(@Param("x") String email);

    Utilisateur findByEmail(String email);
}
