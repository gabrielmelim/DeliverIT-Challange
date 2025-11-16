package br.com.deliverit.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CreatePayAccountResponseDTO {

    private Long id;
    private String name;
    private BigDecimal originalAmount;
    private LocalDate paymentDate;
}