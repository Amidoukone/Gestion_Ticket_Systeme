package com.master.gestion_ticket.service;

import com.master.gestion_ticket.entity.BaseDeConnaissances;
import com.master.gestion_ticket.repository.BaseDeConnaissancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDeConnaissancesService {
    @Autowired
    private BaseDeConnaissancesRepository baseDeConnaissancesRepository;

    public BaseDeConnaissances createBaseDeConnaissances(BaseDeConnaissances baseDeConnaissances) {
        return baseDeConnaissancesRepository.save(baseDeConnaissances);
    }

    public List<BaseDeConnaissances> getAllBaseDeConnaissances() {
        return baseDeConnaissancesRepository.findAll();
    }

    public BaseDeConnaissances updateBaseDeConnaissances(Long id, BaseDeConnaissances baseDeConnaissances) {
        baseDeConnaissances.setId(id);
        return baseDeConnaissancesRepository.save(baseDeConnaissances);
    }

    public void deleteBaseDeConnaissances(Long id) {
        baseDeConnaissancesRepository.deleteById(id);
    }
}
