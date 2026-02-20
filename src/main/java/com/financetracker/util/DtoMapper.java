package com.financetracker.util;

import com.financetracker.dto.response.*;
import com.financetracker.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    // User mappings
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .currencyCode(user.getCurrencyCode())
                .emailVerified(user.getEmailVerified())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // Account mappings
    public static AccountResponse toAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .type(account.getType())
                .currencyCode(account.getCurrencyCode())
                .initialBalance(account.getInitialBalance())
                .currentBalance(account.getCurrentBalance())
                .isActive(account.getIsActive())
                .transactionCount((long) account.getTransactions().size())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

    public static List<AccountResponse> toAccountResponseList(List<Account> accounts) {
        return accounts.stream()
                .map(DtoMapper::toAccountResponse)
                .collect(Collectors.toList());
    }

    // Category mappings
    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .color(category.getColor())
                .icon(category.getIcon())
                .isSystem(category.isSystemCategory())
                .isActive(category.getIsActive ())
                .transactionCount(0L) // Will be populated by service if needed
                .createdAt(category.getCreatedAt())
                .build();
    }

    public static List<CategoryResponse> toCategoryResponseList(List<Category> categories) {
        return categories.stream()
                .map(DtoMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    // Transaction mappings
    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccount().getId())
                .accountName(transaction.getAccount().getName())
                .categoryId(transaction.getCategory() != null ? transaction.getCategory().getId() : null)
                .categoryName(transaction.getCategory() != null ? transaction.getCategory().getName() : null)
                .categoryColor(transaction.getCategory() != null ? transaction.getCategory().getColor() : null)
                .transactionDate(transaction.getTransactionDate())
                .description(transaction.getDescription())
                .merchantName(transaction.getMerchantName())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .notes(transaction.getNotes())
                .isRecurring(transaction.getIsRecurring())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();
    }

    public static List<TransactionResponse> toTransactionResponseList(List<Transaction> transactions) {
        return transactions.stream()
                .map(DtoMapper::toTransactionResponse)
                .collect(Collectors.toList());
    }

    // Goal mappings
    public static GoalResponse toGoalResponse(Goal goal) {
        GoalResponse response = GoalResponse.builder()
                .id(goal.getId())
                .name(goal.getName())
                .targetAmount(goal.getTargetAmount())
                .currentAmount(goal.getCurrentAmount())
                .targetDate(goal.getTargetDate())
                .priority(goal.getPriority())
                .status(goal.getStatus())
                .progressPercentage(goal.getProgressPercentage())
                .createdAt(goal.getCreatedAt())
                .updatedAt(goal.getUpdatedAt())
                .build();

        response.calculateDaysRemaining();
        return response;
    }

    public static List<GoalResponse> toGoalResponseList(List<Goal> goals) {
        return goals.stream()
                .map(DtoMapper::toGoalResponse)
                .collect(Collectors.toList());
    }

    // CSV Upload mappings
    public static CsvUploadResponse toCsvUploadResponse(CsvUpload upload) {
        return CsvUploadResponse.builder()
                .id(upload.getId())
                .accountId(upload.getAccount().getId())
                .accountName(upload.getAccount().getName())
                .fileName(upload.getFileName())
                .fileSize(upload.getFileSize())
                .totalRows(upload.getTotalRows())
                .importedRows(upload.getImportedRows())
                .failedRows(upload.getFailedRows())
                .status(upload.getStatus())
                .errorMessage(upload.getErrorMessage())
                .uploadedAt(upload.getUploadedAt())
                .processedAt(upload.getProcessedAt())
                .build();
    }

    // Page mappings
    public static <T, R> com.financetracker.dto.common.PageResponse<R> toPageResponse(
            Page<T> page,
            List<R> content
    ) {
        return com.financetracker.dto.common.PageResponse.<R>builder()
                .content(content)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .size(page.getSize())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}