package com.example.agreementhandler.service;

import com.example.agreementhandler.entity.Agreement;
import com.example.agreementhandler.entity.Statistic;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgreementService {

    private static int count = 1;

    private final List<Agreement> agreements = new ArrayList<>();

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

    public List<Agreement> getAll(Integer clientId, Integer productId) {
        return filter(clientId, productId);
    }

    public boolean delete(Integer id) {
        Agreement agreementToDelete = agreements.stream()
                .filter(agreement -> agreement.getAgreementId().equals(id))
                .findFirst().orElse(null);
        return agreements.remove(agreementToDelete);
    }

    public Statistic getStatistic(Integer clientId, Integer productId) {
        List<Agreement> result = filter(clientId, productId);
        BigDecimal minAmount = new BigDecimal("1000000000000000");
        BigDecimal maxAmount = new BigDecimal(0);
        BigDecimal sum = new BigDecimal(0);
        for (Agreement agreement : result) {
            BigDecimal amount = agreement.getAmount();
            minAmount = minAmount.min(amount);
            maxAmount = maxAmount.max(amount);
            sum = sum.add(amount);
        }
        return new Statistic(result.size(), minAmount, maxAmount, sum);
    }

    private List<Agreement> filter(Integer clientId, Integer productId) {
        if (clientId == null && productId == null) {
            return agreements;
        }
        List<Agreement> result;
        if (clientId != null && productId == null) {
            result = agreements.stream()
                    .filter(agreement -> agreement.getClientId().equals(clientId))
                    .collect(Collectors.toList());
        } else if (clientId == null) {
            result = agreements.stream()
                    .filter(agreement -> agreement.getProductId().equals(productId))
                    .collect(Collectors.toList());
        } else {
            result = agreements.stream()
                    .filter(agreement -> agreement.getClientId().equals(clientId))
                    .filter(agreement -> agreement.getProductId().equals(productId))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
