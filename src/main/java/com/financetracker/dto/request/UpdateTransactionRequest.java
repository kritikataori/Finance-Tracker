package com.financetracker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransactionRequest {

    private Long categoryId;
    private LocalDate transactionDate;
    private String description;
    private String merchantName;
    private BigDecimal amount;
    private String notes;
    private Boolean isRecurring;
}
