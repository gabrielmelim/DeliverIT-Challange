package br.com.deliverit.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LatePaymentRuleServiceTest {

    private final LatePaymentRuleService service = new LatePaymentRuleService();

    @Test
    @DisplayName("Deve retornar multa e juros zero para atraso zero ou negativo")
    void testNoLateDays() {
        var rule = service.findRule(0);
        assertEquals(BigDecimal.ZERO, rule.fine());
        assertEquals(BigDecimal.ZERO, rule.dailyInterest());

        rule = service.findRule(-5);
        assertEquals(BigDecimal.ZERO, rule.fine());
        assertEquals(BigDecimal.ZERO, rule.dailyInterest());
    }

    @Test
    @DisplayName("Deve retornar multa 2% e juros 0.1% para até 3 dias de atraso")
    void testUpTo3LateDays() {
        var rule = service.findRule(1);
        assertEquals(new BigDecimal("2.0"), rule.fine());
        assertEquals(new BigDecimal("0.1"), rule.dailyInterest());

        rule = service.findRule(3);
        assertEquals(new BigDecimal("2.0"), rule.fine());
        assertEquals(new BigDecimal("0.1"), rule.dailyInterest());
    }

    @Test
    @DisplayName("Deve retornar multa 3% e juros 0.2% para até 5 dias de atraso")
    void testUpTo5LateDays() {
        var rule = service.findRule(4);
        assertEquals(new BigDecimal("3.0"), rule.fine());
        assertEquals(new BigDecimal("0.2"), rule.dailyInterest());

        rule = service.findRule(5);
        assertEquals(new BigDecimal("3.0"), rule.fine());
        assertEquals(new BigDecimal("0.2"), rule.dailyInterest());
    }

    @Test
    @DisplayName("Deve retornar multa 5% e juros 0.3% para mais de 5 dias de atraso")
    void testMoreThan5LateDays() {
        var rule = service.findRule(6);
        assertEquals(new BigDecimal("5.0"), rule.fine());
        assertEquals(new BigDecimal("0.3"), rule.dailyInterest());

        rule = service.findRule(100);
        assertEquals(new BigDecimal("5.0"), rule.fine());
        assertEquals(new BigDecimal("0.3"), rule.dailyInterest());
    }
}
