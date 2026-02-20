package com.financetracker.dto.response;

import com.financetracker.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long id;
    private String name;
    private Account.AccountType type;
    private String currencyCode;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private Boolean isActive;
    private Long transactionCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
