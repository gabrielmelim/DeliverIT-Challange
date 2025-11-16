package br.com.deliverit.service;

import br.com.deliverit.domain.PayAccount;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Data
public class PayAccountCalculator {

    private final LatePaymentRuleService latePaymentRuleService;

    public PayAccount create(String name,
                             BigDecimal originalAmount,
                             LocalDate dueDate,
                             LocalDate paymentDate) {

        int lateDays = (int) ChronoUnit.DAYS.between(dueDate, paymentDate);
        if (lateDays < 0) lateDays = 0;

        var rule = latePaymentRuleService.findRule(lateDays);

        BigDecimal adjustedAmount = calculate(
                originalAmount,
                lateDays,
                rule.fine(),
                rule.dailyInterest()
        );

        return PayAccount.builder()
                .name(name)
                .originalAmount(originalAmount)
                .adjustedAmount(adjustedAmount)
                .lateDays(lateDays)
                .finePercentage(rule.fine())
                .dailyInterestPercentage(rule.dailyInterest())
                .dueDate(dueDate)
                .paymentDate(paymentDate)
                .build();
    }

    private BigDecimal calculate(BigDecimal original,
                                 int lateDays,
                                 BigDecimal fine,
                                 BigDecimal dailyInterest) {

        if (lateDays <= 0)
            return original.setScale(2, RoundingMode.HALF_UP);

        BigDecimal oneHundred = new BigDecimal("100");

        BigDecimal fineAmount = original.multiply(fine).divide(oneHundred, 6, RoundingMode.HALF_UP);
        BigDecimal interestAmount = original.multiply(dailyInterest).divide(oneHundred, 6, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(lateDays));

        return original.add(fineAmount).add(interestAmount)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
