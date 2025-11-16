package br.com.deliverit.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LatePaymentRuleService {

    public record Rule(BigDecimal fine, BigDecimal dailyInterest) {}

    public Rule findRule(int lateDays) {

        if (lateDays <= 0) {
            return new Rule(BigDecimal.ZERO, BigDecimal.ZERO);
        }
        if (lateDays <= 3) {
            return new Rule(new BigDecimal("2.0"), new BigDecimal("0.1"));
        }
        if (lateDays <= 5) {
            return new Rule(new BigDecimal("3.0"), new BigDecimal("0.2"));
        }
        return new Rule(new BigDecimal("5.0"), new BigDecimal("0.3"));
    }
}
