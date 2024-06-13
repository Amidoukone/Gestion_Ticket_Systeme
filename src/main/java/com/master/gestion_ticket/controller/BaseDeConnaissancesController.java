package com.master.gestion_ticket.controller;


import com.master.gestion_ticket.entity.BaseDeConnaissances;
import com.master.gestion_ticket.service.BaseDeConnaissancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base-de-connaissances")
public class BaseDeConnaissancesController {
    @Autowired
    private BaseDeConnaissancesService baseDeConnaissancesService;

    @PostMapping
    public ResponseEntity<BaseDeConnaissances> createBaseDeConnaissances(@RequestBody BaseDeConnaissances baseDeConnaissances) {
        BaseDeConnaissances createdBaseDeConnaissances = baseDeConnaissancesService.createBaseDeConnaissances(baseDeConnaissances);
        return ResponseEntity.ok(createdBaseDeConnaissances);
    }

    @GetMapping
    public ResponseEntity<List<BaseDeConnaissances>> getAllBaseDeConnaissances() {
        List<BaseDeConnaissances> baseDeConnaissancesList = baseDeConnaissancesService.getAllBaseDeConnaissances();
        return ResponseEntity.ok(baseDeConnaissancesList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseDeConnaissances> updateBaseDeConnaissances(@PathVariable Long id, @RequestBody BaseDeConnaissances baseDeConnaissances) {
        BaseDeConnaissances updatedBaseDeConnaissances = baseDeConnaissancesService.updateBaseDeConnaissances(id, baseDeConnaissances);
        return ResponseEntity.ok(updatedBaseDeConnaissances);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaseDeConnaissances(@PathVariable Long id) {
        baseDeConnaissancesService.deleteBaseDeConnaissances(id);
        return ResponseEntity.noContent().build();
    }
}
