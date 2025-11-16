package br.com.deliverit.service;

import br.com.deliverit.domain.PayAccount;
import br.com.deliverit.dto.CreatePayAccountResponseDTO;
import br.com.deliverit.dto.PayAccountRequestDTO;
import br.com.deliverit.dto.PayAccountResponseDTO;
import br.com.deliverit.exception.ValidationException;
import br.com.deliverit.repository.PaymentAccountRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PayAccountServiceTest {

    private PaymentAccountRepositoryImpl repository;
    private PayAccountCalculator calculator;
    private PayAccountService service;

    @BeforeEach
    void setUp() {
        repository = mock(PaymentAccountRepositoryImpl.class);
        calculator = mock(PayAccountCalculator.class);
        service = new PayAccountService(repository, calculator);
    }

    @Test
    @DisplayName("Deve criar conta a pagar com sucesso")
    void testCreateSuccess() {
        PayAccountRequestDTO dto = new PayAccountRequestDTO();
        dto.setName("Conta Teste");
        dto.setOriginalAmount(new BigDecimal("100.00"));
        dto.setDueDate(LocalDate.now().minusDays(2));
        dto.setPaymentDate(LocalDate.now());

        PayAccount payAccount = PayAccount.builder()
                .id(1L)
                .name(dto.getName())
                .originalAmount(dto.getOriginalAmount())
                .adjustedAmount(new BigDecimal("102.00"))
                .lateDays(2)
                .finePercentage(new BigDecimal("2.0"))
                .dailyInterestPercentage(new BigDecimal("0.1"))
                .dueDate(dto.getDueDate())
                .paymentDate(dto.getPaymentDate())
                .build();

        when(calculator.create(any(), any(), any(), any())).thenReturn(payAccount);
        when(repository.create(any())).thenReturn(payAccount);

        CreatePayAccountResponseDTO response = service.create(dto);
        assertEquals(1L, response.getId());
        assertEquals("Conta Teste", response.getName());
        assertEquals(new BigDecimal("100.00"), response.getOriginalAmount());
        assertEquals(dto.getPaymentDate(), response.getPaymentDate());
    }

    @Test
    @DisplayName("Deve lançar exceção se nome for vazio")
    void testCreateValidationName() {
        PayAccountRequestDTO dto = new PayAccountRequestDTO();
        dto.setName("");
        dto.setOriginalAmount(new BigDecimal("100.00"));
        dto.setDueDate(LocalDate.now());
        dto.setPaymentDate(LocalDate.now());
        assertThrows(ValidationException.class, () -> service.create(dto));
    }

    @Test
    @DisplayName("Deve listar contas a pagar")
    void testList() {
        PayAccount payAccount = PayAccount.builder()
                .id(1L)
                .name("Conta Teste")
                .originalAmount(new BigDecimal("100.00"))
                .adjustedAmount(new BigDecimal("102.00"))
                .lateDays(2)
                .finePercentage(new BigDecimal("2.0"))
                .dailyInterestPercentage(new BigDecimal("0.1"))
                .dueDate(LocalDate.now().minusDays(2))
                .paymentDate(LocalDate.now())
                .build();
        when(repository.list()).thenReturn(List.of(payAccount));
        List<PayAccountResponseDTO> list = service.list();
        assertEquals(1, list.size());
        PayAccountResponseDTO dto = list.get(0);
        assertEquals("Conta Teste", dto.getName());
        assertEquals(new BigDecimal("100.00"), dto.getOriginalAmount());
        assertEquals(new BigDecimal("102.00"), dto.getAdjustedAmount());
        assertEquals(2, dto.getLateDays());
        assertEquals(payAccount.getPaymentDate(), dto.getPaymentDate());
    }
}
