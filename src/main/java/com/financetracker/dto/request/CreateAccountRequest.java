package com.financetracker.dto.request;

import com.financetracker.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {

    @NotBlank(message = "Account name is required")
    @Size(max = 100, message = "Account name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Account type is required")
    private Account.AccountType type;

    @Size(min = 3, max = 3, message = "Currency code must be 3 characters (e.g., INR)")
    @Builder.Default
    private String currencyCode = "INR";

    @Builder.Default
    private BigDecimal initialBalance = BigDecimal.ZERO;
}
