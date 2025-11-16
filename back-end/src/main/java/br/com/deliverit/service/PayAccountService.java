package br.com.deliverit.service;

import br.com.deliverit.domain.PayAccount;
import br.com.deliverit.dto.PayAccountRequestDTO;
import br.com.deliverit.dto.PayAccountResponseDTO;
import br.com.deliverit.dto.CreatePayAccountResponseDTO;
import br.com.deliverit.exception.ValidationException;
import br.com.deliverit.repository.PaymentAccountRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayAccountService {

    private final PaymentAccountRepositoryImpl repository;
    private final PayAccountCalculator calculator;

    @Transactional
    public CreatePayAccountResponseDTO create(PayAccountRequestDTO dto) {

        validate(dto);

        PayAccount entity = calculator.create(
                dto.getName(),
                dto.getOriginalAmount(),
                dto.getDueDate(),
                dto.getPaymentDate()
        );

        return toCreateDTO(repository.create(entity));
    }

    @Transactional
    public List<PayAccountResponseDTO> list() {
        return repository.list()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private void validate(PayAccountRequestDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ValidationException("O nome da conta é obrigatório.");
        }

        if (dto.getOriginalAmount() == null) {
            throw new ValidationException("O valor original é obrigatório.");
        }

        if (dto.getOriginalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("O valor original deve ser maior que zero.");
        }

        if (dto.getDueDate() == null) {
            throw new ValidationException("A data de vencimento é obrigatória.");
        }

        if (dto.getPaymentDate() == null) {
            throw new ValidationException("A data de pagamento é obrigatória.");
        }
    }

    private PayAccountResponseDTO toDTO(PayAccount c) {
        return PayAccountResponseDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .originalAmount(c.getOriginalAmount())
                .adjustedAmount(c.getAdjustedAmount())
                .lateDays(c.getLateDays())
                .paymentDate(c.getPaymentDate())
                .build();
    }

    private CreatePayAccountResponseDTO toCreateDTO(PayAccount c) {
        return CreatePayAccountResponseDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .originalAmount(c.getOriginalAmount())
                .paymentDate(c.getPaymentDate())
                .build();
    }
}
