package com.financetracker.dto.response;

import com.financetracker.entity.Account;
import com.financetracker.entity.Category;
import com.financetracker.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long id;
    private Long accountId;
    private String accountName;
    private Long categoryId;
    private String categoryName;
    private String categoryColor;
    private LocalDate transactionDate;
    private String description;
    private String merchantName;
    private BigDecimal amount;
    private Transaction.TransactionType type;
    private String notes;
    private Boolean isRecurring;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
