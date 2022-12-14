package com.example.agreementhandler.controller;

import com.example.agreementhandler.entity.Agreement;
import com.example.agreementhandler.entity.Statistic;
import com.example.agreementhandler.service.AgreementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AgreementController {

    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping("/agreements")
    public ResponseEntity<Agreement> create(@RequestBody @Valid Agreement agreement) {
        return ResponseEntity.ok(agreementService.create(agreement));
    }

    @GetMapping("/agreements/{id}")
    public ResponseEntity<Agreement> getById(@PathVariable Integer id) {
        Agreement result = agreementService.getById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/agreements")
    public ResponseEntity<List<Agreement>> getAll(@RequestParam(required = false) Integer clientId,
                                                  @RequestParam(required = false) Integer productId) {
        return ResponseEntity.ok(agreementService.getAll(clientId, productId));
    }

    @DeleteMapping("/agreements/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (!agreementService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("");
    }

    @GetMapping("/statistics")
    public ResponseEntity<Statistic> getStatistic(@RequestParam(required = false) Integer clientId,
                                                  @RequestParam(required = false) Integer productId) {
        return ResponseEntity.ok(agreementService.getStatistic(clientId, productId));
    }
}
