package br.com.deliverit.service;

import br.com.deliverit.domain.PayAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PayAccountCalculatorTest {

    private LatePaymentRuleService latePaymentRuleService;
    private PayAccountCalculator calculator;

    @BeforeEach
    void setUp() {
        latePaymentRuleService = mock(LatePaymentRuleService.class);
        calculator = new PayAccountCalculator(latePaymentRuleService);
    }

    @Test
    @DisplayName("Deve criar PayAccount sem multa e juros quando não há atraso")
    void testCreateNoLate() {
        when(latePaymentRuleService.findRule(0)).thenReturn(new LatePaymentRuleService.Rule(BigDecimal.ZERO, BigDecimal.ZERO));
        LocalDate today = LocalDate.now();
        PayAccount account = calculator.create("Conta Teste", new BigDecimal("100.00"), today, today);
        assertEquals(new BigDecimal("100.00"), account.getAdjustedAmount());
        assertEquals(0, account.getLateDays());
        assertEquals(BigDecimal.ZERO, account.getFinePercentage());
        assertEquals(BigDecimal.ZERO, account.getDailyInterestPercentage());
    }

    @Test
    @DisplayName("Deve criar PayAccount com multa e juros para atraso de 4 dias")
    void testCreateWithLate() {
        when(latePaymentRuleService.findRule(4)).thenReturn(new LatePaymentRuleService.Rule(new BigDecimal("3.0"), new BigDecimal("0.2")));
        LocalDate due = LocalDate.now().minusDays(4);
        LocalDate payment = LocalDate.now();
        PayAccount account = calculator.create("Conta Teste", new BigDecimal("200.00"), due, payment);
        assertEquals(4, account.getLateDays());
        assertEquals(new BigDecimal("3.0"), account.getFinePercentage());
        assertEquals(new BigDecimal("0.2"), account.getDailyInterestPercentage());
        // Cálculo: 200 + 6 (3%) + 1.6 (0.2% * 4 dias) = 207.60
        assertEquals(new BigDecimal("207.60"), account.getAdjustedAmount());
    }

    @Test
    @DisplayName("Deve considerar atraso negativo como zero")
    void testNegativeLateDays() {
        when(latePaymentRuleService.findRule(0)).thenReturn(new LatePaymentRuleService.Rule(BigDecimal.ZERO, BigDecimal.ZERO));
        LocalDate due = LocalDate.now().plusDays(2);
        LocalDate payment = LocalDate.now();
        PayAccount account = calculator.create("Conta Teste", new BigDecimal("150.00"), due, payment);
        assertEquals(0, account.getLateDays());
        assertEquals(new BigDecimal("150.00"), account.getAdjustedAmount());
    }
}
