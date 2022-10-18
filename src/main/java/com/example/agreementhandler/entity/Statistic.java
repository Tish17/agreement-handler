package com.example.agreementhandler.entity;

import java.math.BigDecimal;

public class Statistic {

    private Integer count;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal sum;

    public Statistic(Integer count, BigDecimal minAmount, BigDecimal maxAmount, BigDecimal sum) {
        this.count = count;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.sum = sum;
    }
}
