package br.com.deliverit.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PayAccountResponseDTO {

    private Long id;
    private String name;
    private BigDecimal originalAmount;
    private BigDecimal adjustedAmount;
    private Integer lateDays;
    private LocalDate paymentDate;
}
