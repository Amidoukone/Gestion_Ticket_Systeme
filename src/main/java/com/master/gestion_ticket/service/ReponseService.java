
package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.Reponse;
import com.master.gestion_ticket.entity.Ticket;
import com.master.gestion_ticket.entity.BaseDeConnaissances;
import com.master.gestion_ticket.repository.ReponseRepository;
import com.master.gestion_ticket.repository.TicketRepository;
import com.master.gestion_ticket.repository.BaseDeConnaissancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReponseService {
    @Autowired
    private ReponseRepository reponseRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BaseDeConnaissancesRepository baseDeConnaissancesRepository;

    public Reponse createReponse(Long ticketId, Reponse reponse) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        reponse.setTicket(ticket);
        reponse.setDateReponse(new Timestamp(System.currentTimeMillis()));
        reponse.setFormateur(reponse.getFormateur());

        Reponse savedReponse = reponseRepository.save(reponse);

        // Ajouter la réponse à la liste des réponses du ticket pour maintenir la relation bidirectionnelle
        //ticket.getReponses().add(savedReponse);
        //ticketRepository.save(ticket);

        return savedReponse;
    }

    public void addReponseToKnowledgeBase(Long ticketId, String titre, String contenu) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        BaseDeConnaissances baseDeConnaissances = new BaseDeConnaissances();
        baseDeConnaissances.setTitre(titre);
        baseDeConnaissances.setContenu(contenu);

        baseDeConnaissancesRepository.save(baseDeConnaissances);
    }

    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    public Reponse updateReponse(Long id, Reponse reponse) {
        reponse.setId(id);
        return reponseRepository.save(reponse);
    }

    public void deleteReponse(Long id) {
        reponseRepository.deleteById(id);
    }
}
