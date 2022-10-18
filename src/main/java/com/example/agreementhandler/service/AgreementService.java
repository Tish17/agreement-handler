package com.example.agreementhandler.service;

import com.example.agreementhandler.entity.Agreement;
import com.example.agreementhandler.entity.Statistic;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgreementService {

    private static int count = 1;

    private List<Agreement> agreements = new ArrayList<>();

    public Agreement create(Agreement agreement) {
        agreement.setAgreementId(count++);
        agreement.setTimestamp(LocalDateTime.now());
        agreements.add(agreement);
        return agreement;
    }

    public Agreement getById(Integer id) {
        return agreements.stream()
                .filter(agreement -> agreement.getAgreementId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Agreement> getAll() {
        return agreements;
    }

    public void delete(Integer id) {
        Agreement agreementToDelete = agreements.stream()
                .filter(agreement -> agreement.getAgreementId().equals(id))
                .findFirst().orElse(null);
        agreements.remove(agreementToDelete);
    }

    public Statistic getStatistic() {
        BigDecimal minAmount = new BigDecimal("1000000000000000");
        BigDecimal maxAmount = new BigDecimal(0);
        BigDecimal sum = new BigDecimal(0);
        for (Agreement agreement : agreements) {
            BigDecimal amount = agreement.getAmount();
            minAmount = minAmount.min(amount);
            maxAmount = maxAmount.max(amount);
            sum = sum.add(amount);
        }
        return new Statistic(agreements.size(), minAmount, maxAmount, sum);
    }
}
