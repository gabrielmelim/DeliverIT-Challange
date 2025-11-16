package br.com.deliverit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pay_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "original_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal originalAmount;

    @Column(name = "adjusted_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal adjustedAmount;

    @Column(name = "late_days", nullable = false)
    private Integer lateDays;

    @Column(name = "fine_percentage", precision = 5, scale = 2, nullable = false)
    private BigDecimal finePercentage;

    @Column(name = "daily_interest_percentage", precision = 5, scale = 3, nullable = false)
    private BigDecimal dailyInterestPercentage;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
}
