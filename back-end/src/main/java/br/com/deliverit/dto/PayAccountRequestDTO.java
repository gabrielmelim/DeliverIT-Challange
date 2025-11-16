package br.com.deliverit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PayAccountRequestDTO {

    private String name;
    private BigDecimal originalAmount;
    private LocalDate dueDate;
    private LocalDate paymentDate;
}
