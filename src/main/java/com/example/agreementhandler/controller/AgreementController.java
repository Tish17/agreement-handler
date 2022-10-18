package com.example.agreementhandler.controller;

import com.example.agreementhandler.entity.Agreement;
import com.example.agreementhandler.entity.Statistic;
import com.example.agreementhandler.service.AgreementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgreementController {

    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping("/agreements")
    public ResponseEntity<Agreement> create(@RequestBody Agreement agreement) {
        return ResponseEntity.ok(agreementService.create(agreement));
    }

    @GetMapping("/agreements/{id}")
    public ResponseEntity<Agreement> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(agreementService.getById(id));
    }

    @GetMapping("/agreements")
    public ResponseEntity<List<Agreement>> getAll() {
        return ResponseEntity.ok(agreementService.getAll());
    }

    @DeleteMapping("/agreements/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable Integer id) {
        agreementService.delete(id);
        return ResponseEntity.ok();
    }

    @GetMapping("/statistics")
    public ResponseEntity<Statistic> getStatistic() {
        return ResponseEntity.ok(agreementService.getStatistic());
    }
}
