package com.example.agreementhandler.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Agreement {

    private Integer agreementId;
    private Integer clientId;
    private Integer productId;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDateTime timestamp;

    public Agreement() {
    }

    public Agreement(Integer clientId, Integer productId, BigDecimal amount, LocalDate startDate) {
        this.clientId = clientId;
        this.productId = productId;
        this.amount = amount;
        this.startDate = startDate;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
